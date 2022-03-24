package cc.caker.netty.demo01.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * BIOServer
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public class BIOServer extends Thread {

    public static void main(String[] args) {
        new BIOServer().start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(10086)) {
            System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈 | 欢迎关注&获取源码}");

            while (true) {
                Socket socket = serverSocket.accept();
                BIOServerHandler handler = new BIOServerHandler(socket, Charset.defaultCharset());
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
