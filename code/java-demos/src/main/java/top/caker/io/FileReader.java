package top.caker.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author cakeralter
 * @date 2020/4/15
 */
public class FileReader {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:", "《穿入聊斋》.txt");
        List<String> lines = Files.readAllLines(path, Charset.forName("GBK"));
        lines.forEach(System.out::println);
    }
}
