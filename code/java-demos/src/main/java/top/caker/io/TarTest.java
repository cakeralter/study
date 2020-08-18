package top.caker.io;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
public class TarTest {

    /**
     * 打包
     */
    @Test
    public void pack() {
        File file = new File("D:/test.tar");
        try (FileOutputStream fos = new FileOutputStream(file);
             TarArchiveOutputStream taos = new TarArchiveOutputStream(fos)) {
            TarArchiveEntry entry1 = new TarArchiveEntry("test1.txt");
            entry1.setSize("我爱中国111".getBytes().length);
            taos.putArchiveEntry(entry1);
            taos.write("我爱中国111".getBytes());
            taos.closeArchiveEntry();

            TarArchiveEntry entry2 = new TarArchiveEntry("test2.txt");
            entry2.setSize("我爱中国222".getBytes().length);
            taos.putArchiveEntry(entry2);
            taos.write("我爱中国222".getBytes());
            taos.closeArchiveEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解包
     */
    @Test
    public void unpack() {
        File file = new File("D:/test.tar");
        try (FileInputStream fis = new FileInputStream(file);
             TarArchiveInputStream tais = new TarArchiveInputStream(fis)) {
            TarArchiveEntry entry;
            while (Objects.nonNull(entry = tais.getNextTarEntry())) {
                byte[] bytes = new byte[(int) entry.getSize()];
                int len = -1;
                while ((len = tais.read(bytes)) != -1) {
                    System.out.println(new String(bytes, 0, len));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
