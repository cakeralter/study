package cc.caker.netty.demo01.nio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * ChannelHandler
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class ChannelHandler {

    private final SocketChannel socketChannel;
    private final Charset charset;

    /**
     * writeAndFlush
     *
     * @param msg
     */
    public void writeAndFlush(Object msg) {
        try {
            byte[] bytes = msg.toString().getBytes(charset);
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
