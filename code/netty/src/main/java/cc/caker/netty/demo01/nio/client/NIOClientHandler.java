package cc.caker.netty.demo01.nio.client;

import cc.caker.netty.demo01.nio.ChannelAdapter;
import cc.caker.netty.demo01.nio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * NIOClientHandler
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public class NIOClientHandler extends ChannelAdapter {

    public NIOClientHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler handler) {
        try {
            System.out.println("链接报告LocalAddress:" + handler.getSocketChannel().getLocalAddress());
            handler.writeAndFlush("hi! 我是bugstack虫洞栈 BioClient to msg for you \r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandler handler, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        handler.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
