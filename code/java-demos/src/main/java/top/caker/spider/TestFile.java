package top.caker.spider;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author wangyl
 * @date 2019/7/30
 * @description
 */
public class TestFile {

    private final static String DIR = "D:\\txt\\";
    private final static String PR_DIR = "D:\\BaiduNetdiskDownload\\";

    public static void main(String[] args) {
        File fileDir = new File(DIR);
        File[] files = fileDir.listFiles();
        Arrays.sort(files, (o1, o2) -> {
            String n1 = o1.getName().replace("（", "").replace("）", "");
            int end1 = n1.contains("-") ? n1.indexOf("-") : n1.indexOf(".");
            int s1 = Integer.parseInt(n1.substring(n1.indexOf("】") + 1, end1));
            String n2 = o2.getName().replace("（", "").replace("）", "");
            int end2 = n2.contains("-") ? n2.indexOf("-") : n2.indexOf(".");
            int s2 = Integer.parseInt(n2.substring(n2.indexOf("】") + 1, end2));

            return s1 - s2;
        });
        StringBuilder total = new StringBuilder(550999);
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder(12000);
                String buf = "";
                while (Objects.nonNull(buf = reader.readLine())) {
                    content.append(buf);
                }
                int start = content.indexOf("作者：keyprca") != -1 ? content.indexOf("作者：keyprca") : 0;
                int end = content.indexOf("版主提醒：阅文后请用你的认真回复支持作者") == -1 ? 0 : content.indexOf("版主提醒：阅文后请用你的认真回复支持作者");
                System.out.println("****************************" + file.getName() + "****************************");
                System.out.println(content.substring(start, end));
                System.out.println("\n\n\n");
                total.append(content.substring(start, end)
                        .replace("&nbsp;", " ")
                        .replace("<br>", "\r\n")
                        .replaceAll("<.*>", ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fos = new FileWriter(new File(PR_DIR + "清茗学院.txt"))) {
            fos.write(total.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
