package cc.caker.netty.demo02.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * NettyClient
 *
 * @author cakeralter
 * @date 2022/4/12
 * @since 1.0
 */
public class NettyClient {

    public static void main(String[] args) {
        // 1.创建启动类
        Bootstrap bootstrap = new Bootstrap();
        // 2.设置工作线程组
        bootstrap.group(new NioEventLoopGroup());
        // 3.设置channel
        bootstrap.channel(NioSocketChannel.class);
        // 4.连接配置
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        // 5.设置pipeline
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyClientHandler());
            }
        });
        try {
            // 6.建立连接
            InetSocketAddress address = new InetSocketAddress("localhost", 10086);
            ChannelFuture channelFuture = bootstrap.connect(address).sync();
            // 7.监听通道关闭事件
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
