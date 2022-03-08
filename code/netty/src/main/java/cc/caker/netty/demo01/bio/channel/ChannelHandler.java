package cc.caker.netty.demo01.bio.channel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * ChannelHandler
 *
 * @author cakeralter
 * @date 2022/3/1
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class ChannelHandler {

    private final Socket socket;
    private final Charset charset;

    /**
     * writeAndFlush
     *
     * @param msg
     */
    public void writeAndFlush(Object msg) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(msg.toString().getBytes(charset));
            os.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
