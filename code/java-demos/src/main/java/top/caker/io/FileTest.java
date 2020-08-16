package top.caker.io;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * File Test
 *
 * @author cakeralter
 * @date 2020/8/16
 */
public class FileTest {

    @Test
    public void testRead() throws IOException {
        long start = System.currentTimeMillis(), end;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/TXT_TOTAL.txt"))) {
            printAllFile(new File("D:/TXT"), writer);
        }
        end = System.currentTimeMillis();
        System.out.println("统计完成，耗时：" + (end - start) + "ms");
    }

    private void printAllFile(File file, BufferedWriter writer) throws IOException {
        if (Objects.isNull(file)) return;
        if (file.isFile())
            writer.write(String.format("%s\t\t%s\t\t%.2fMB\n", file.getParent(), file.getName(), file.length() * 1.0 / 1024 / 1024));
        else {
            File[] files = file.listFiles();
            if (Objects.isNull(files)) return;
            writer.write("\n***********" + file.getAbsolutePath() + "***********\n");
            for (File f : files) {
                printAllFile(f, writer);
            }
        }
    }
}
