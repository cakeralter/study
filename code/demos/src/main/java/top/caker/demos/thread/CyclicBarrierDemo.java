package top.caker.demos.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier(循环栅栏)也是一种多线程并发控制的实用工具，
 * 和 CountDownLatch 一样具有等待计数的功能，但是相比于 CountDownLatch 功能更加强大。
 * <p>
 * 场景：公司每天开3次会，每次要所有人签到发言
 *
 * @author cakeralter
 * @date 2020/4/21
 */
public class CyclicBarrierDemo {

    private final static int PEOPLE = 5;
    private final static int MEETING = 5;
    private static CyclicBarrier barrier = new CyclicBarrier(PEOPLE, () -> {
        System.out.println("人到齐了, 开会了");
    });

    public static void main(String[] args) {
        for (int i = 1; i <= PEOPLE; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "签到");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "发言");
            }, String.valueOf(i)).start();
        }
    }
}
