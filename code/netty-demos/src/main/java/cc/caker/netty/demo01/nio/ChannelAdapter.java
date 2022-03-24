package cc.caker.netty.demo01.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * ChannelAdapter
 *
 * @author cakeralter
 * @date 2022/3/8
 * @since 1.0
 */
public abstract class ChannelAdapter extends Thread {

    private Selector selector;
    private ChannelHandler channelHandler;
    private Charset charset;

    public ChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    handleInput(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * handleInput
     *
     * @param selectionKey
     */
    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (!selectionKey.isValid()) {
            return;
        }

        Class<?> superclass = selectionKey.channel().getClass().getSuperclass();
        if (superclass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // connect
            if (selectionKey.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    channelHandler = new ChannelHandler(socketChannel, charset);
                    channelActive(channelHandler);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else {
                    System.exit(1);
                }
            }

            // read
            if (selectionKey.isReadable()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    channelRead(channelHandler, new String(bytes, charset));
                } else if (read < 0) {
                    selectionKey.cancel();
                    socketChannel.close();
                }
            }
        }

        if (superclass == ServerSocketChannel.class) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            if (selectionKey.isAcceptable()) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

                channelHandler = new ChannelHandler(socketChannel, charset);
                channelActive(channelHandler);
            }
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
