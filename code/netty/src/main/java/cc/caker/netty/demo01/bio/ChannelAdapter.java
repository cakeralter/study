package cc.caker.netty.demo01.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * ChannelAdapter
 *
 * @author cakeralter
 * @date 2022/3/1
 * @since 1.0
 */
public abstract class ChannelAdapter extends Thread {

    private Socket socket;
    private ChannelHandler channelHandler;
    private Charset charset;

    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        while (!socket.isConnected()) {
            break;
        }
        channelHandler = new ChannelHandler(socket, charset);
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while ((msg = reader.readLine()) != null) {
                channelRead(channelHandler, msg);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 处理连接
     *
     * @param handler
     */
    public abstract void channelActive(ChannelHandler handler);

    /**
     * 处理读
     *
     * @param handler
     */
    public abstract void channelRead(ChannelHandler handler, Object msg);
}
