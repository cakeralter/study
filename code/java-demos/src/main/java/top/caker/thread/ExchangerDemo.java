package top.caker.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Exchanger 是一个用于线程间协作的工具类，用于两个线程间能够交换。
 * 它提供了一个交换的同步点，在这个同步点两个线程能够交换数据。
 * 具体交换数据是通过 exchange 方法来实现的，如果一个线程先执行 exchange 方法，
 * 那么它会同步等待另一个线程也执行 exchange 方法，这个时候两个线程就都达到了同步点，两个线程就可以交换数据。
 * <p>
 * 场景：同学甲和乙去图书馆买书
 *
 * @author cakeralter
 * @date 2020/4/21
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("甲同学正在选书...");
            try {
                String book = exchanger.exchange("《Java从入门到入坟》");
                System.out.println("甲看到乙买的书" + book);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("乙同学正在选书...");
            try {
                TimeUnit.SECONDS.sleep(3);
                String book = exchanger.exchange("《五年高考 三年模拟》");
                System.out.println("乙看到甲买的书" + book);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
