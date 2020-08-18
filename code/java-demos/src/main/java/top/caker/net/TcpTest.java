package top.caker.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author cakeralter
 * @date 2020/8/17
 */
public class TcpTest {

    public void testServer() {
        try (ServerSocket serverSocket = new ServerSocket(8848)) {
            System.out.println("Server is starting...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream is = socket.getInputStream();
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader reader = new BufferedReader(isr)) {
                    System.out.println(socket.getInetAddress().getHostAddress() + "：" + reader.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testClient() {
        while (true) {
            try (Socket socket = new Socket("127.0.0.1", 8848);
                 OutputStream os = socket.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os);
                 BufferedWriter writer = new BufferedWriter(osw)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("请输入：");
                writer.write(scanner.nextLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TcpTest()::testServer).start();
        new Thread(new TcpTest()::testClient).start();
    }
}
