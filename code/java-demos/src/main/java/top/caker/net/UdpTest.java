package top.caker.net;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author cakeralter
 * @date 2020/8/17
 */
public class UdpTest {

    @Test
    public void testServer() {
        byte[] buf = new byte[1024];
        try (DatagramSocket socket = new DatagramSocket(8848)) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            System.out.println(new String(buf, 0, buf.length));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClient() {
        byte[] buf = "我爱中华".getBytes();
        try {
            InetAddress address = InetAddress.getLocalHost();
            try (DatagramSocket socket = new DatagramSocket()) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 8848);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
