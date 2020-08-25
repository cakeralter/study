package top.caker.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author wangyl
 * @date 2019/7/30
 * @description
 */
public class TestSis implements Callable<Object> {

    private final static String URL = "http://104.194.212.8/forum/forumdisplay.php?fid=322&filter=type&typeid=1373&page=";
    //    private final static String URL = "http://104.194.212.8/forum/forum-383-";
    private final static String HOST = "http://38.103.161.52/bbs/";
    private final static int MAX_PAGE = 3;
    private final static String DIR = "d:\\txt\\";

    private String url;

    private void setUrl(String url) {
        this.url = url;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Callable<Object>> callables = new ArrayList<>(MAX_PAGE);

        for (int i = 1; i < MAX_PAGE; i++) {
            TestSis sis = new TestSis();
            sis.setUrl(URL + i);
            callables.add(sis);
        }

        List<Future<Object>> futures = service.invokeAll(callables, 5, TimeUnit.SECONDS);
        for (Future<Object> future : futures) {
            while (!future.isDone()) ;
        }
        service.shutdown();
    }

    private static void spider(String url) {
        Document document = get(url);
        if (Objects.isNull(document)) {
            return;
        }
        Elements elements = document.select("a");
        for (Element element : elements) {
            if (element.text().contains("清茗学院")) {
                String contentUrl = element.attr("href");
                Document contentDoc = get(HOST + contentUrl);
                if (Objects.isNull(contentDoc)) {
                    continue;
                }
                String content = Optional.ofNullable(contentDoc.select("div.t_msgfont")).map(x -> x.get(0)).map(Element::html).orElse("");
                write(DIR + element.text() + ".txt", content);
            }
        }
    }

    private static Document get(String url) {
        try {
            System.out.println(Thread.currentThread().getName() + "********开始解析" + url);
            return Jsoup.connect(url).timeout(7000).proxy("127.0.0.1", 1080).get();
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + "******解析" + url + "失败********");
            return null;
        }
    }

    private static void write(String file, String content) {
        try (FileOutputStream fos = new FileOutputStream(new File(file))) {
            fos.write(content.getBytes());
            System.out.println(Thread.currentThread().getName() + "**********创建文件<" + file + ">成功************");
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName() + "**********创建文件<" + file + ">失败************");
        }
    }

    @Override
    public Object call() throws Exception {
        spider(url);

        System.out.println(Thread.currentThread().getName() + "解析完结");
        return null;
    }
}
