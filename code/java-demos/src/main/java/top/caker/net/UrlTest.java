package top.caker.net;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author cakeralter
 * @date 2020/8/17
 */
public class UrlTest {

    @Test
    public void testUrl() throws IOException {
        URL url = new URL("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3257906088,467946102&fm=26&gp=0.jpg");
        final URLConnection connection = url.openConnection();
        connection.connect();
        try (InputStream is = connection.getInputStream();
             BufferedInputStream bis = new BufferedInputStream(is);
             OutputStream out = new FileOutputStream("D:/iliya.jpg");
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bis.read(bytes)) != -1)
                bos.write(bytes, 0, len);
        }
    }
}
