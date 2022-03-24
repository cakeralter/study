package cc.caker.netty.demo01.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * NIOClient
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        boolean isConnect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 10010));
        if (isConnect) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        System.out.println("itstack-demo-netty client start done. {关注公众号：bugstack虫洞栈 | 欢迎关注&获取源码}");
        new NIOClientHandler(selector, Charset.defaultCharset()).start();
    }
}
