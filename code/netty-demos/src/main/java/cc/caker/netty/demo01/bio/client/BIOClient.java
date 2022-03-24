package cc.caker.netty.demo01.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * BioClient
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public class BIOClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 10086);
            BIOClientHandler handler = new BIOClientHandler(socket, Charset.defaultCharset());
            System.out.println("itstack-demo-netty client start done. {关注公众号：bugstack虫洞栈 | 欢迎关注&获取源码}");
            handler.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
