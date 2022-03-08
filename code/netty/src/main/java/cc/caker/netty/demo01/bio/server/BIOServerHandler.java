package cc.caker.netty.demo01.bio.server;

import cc.caker.netty.demo01.bio.channel.ChannelAdapter;
import cc.caker.netty.demo01.bio.channel.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BIOServerHandler
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public class BIOServerHandler extends ChannelAdapter {

    public BIOServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler handler) {
        System.out.println("链接报告LocalAddress:" + handler.getSocket().getLocalAddress());
        handler.writeAndFlush("hi! 我是bugstack虫洞栈 BioServer to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler handler, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        handler.writeAndFlush("hi Server已经收到Client的消息Success！\r\n");
    }
}
