package cc.caker.netty.demo02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NettyServer
 *
 * @author cakeralter
 * @date 2022/4/12
 * @since 1.0
 */
public class NettyServer {

    public static void main(String[] args) {
        // 1.创建启动器
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 2.设置工作线程
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(boss, worker);
        // 3.设置channel
        bootstrap.channel(NioServerSocketChannel.class);
        // 4.连接配置
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        // 5.设置pipeline
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new NettyServerHandler());
            }
        });
        // 6.绑定端口
        try {
            ChannelFuture channelFuture = bootstrap.bind(10086).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
