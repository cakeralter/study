package top.caker.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author cakeralter
 * @date 2020/8/12
 */
public class JsoupTest {

    @Test
    public void testParse() throws IOException {
        Document document = Jsoup.connect("http://104.194.212.8/forum/thread-10778561-1-1.html")
                .proxy("127.0.0.1", 1080).post();
        Elements elements = document.select(".t_msgfont");
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("D:/xlcs.txt")))) {
            String text = elements.get(1).text();
            bos.write(text.getBytes());
        }
    }

    @Test
    public void testSpider() throws IOException {
        Document document = Jsoup.connect("http://104.194.212.8/forum/thread-10778561-1-1.html")
                .proxy("127.0.0.1", 1080).post();
    }
}
