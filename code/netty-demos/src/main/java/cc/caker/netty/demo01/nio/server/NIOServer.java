package cc.caker.netty.demo01.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

/**
 * NIOServer
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public class NIOServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        new NIOServer().bind(10010);
    }

    public void bind(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈 | 欢迎关注&获取源码}");
            new NIOServerHandler(selector, Charset.defaultCharset()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
