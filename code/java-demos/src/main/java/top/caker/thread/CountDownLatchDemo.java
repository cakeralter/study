package top.caker.thread;

import java.util.SplittableRandom;
import java.util.concurrent.CountDownLatch;

/**
 * 在多线程协作完成业务功能时，有时候需要等待其他多个线程完成任务之后，主线程才能继续往下执行业务功能。
 * 在这种的业务场景下，通常可以使用 Thread 类的 join 方法，让主线程等待被 join 的线程执行完之后，主线程才能继续往下执行。
 * java 并发工具类中为我们提供了类似“倒计时”这样的工具类CountDownLatchDemo，可以十分方便的完成所说的这种业务场景。
 * <p>
 * 场景：
 * 30个学生一起春游
 * 学校大巴需要等所有人上车才能发车
 *
 * @author cakeralter
 * @date 2020/4/21
 */
public class CountDownLatchDemo {

    private final static SplittableRandom random = new SplittableRandom();
    private final static int STUDENTS = 30;
    private static CountDownLatch studentLatch = new CountDownLatch(STUDENTS);
    private static CountDownLatch busLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        for (int i = 1; i <= STUDENTS; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(random.split().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                studentLatch.countDown();
                System.out.println("学生" + Thread.currentThread().getName() + "上车");
            }, String.valueOf(i)).start();
        }

        try {
            studentLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("学生到齐了，可以发车了");
    }
}
