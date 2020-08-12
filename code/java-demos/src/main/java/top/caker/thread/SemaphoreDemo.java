package top.caker.thread;

import java.util.SplittableRandom;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 可以理解为信号量，用于控制资源能够被并发访问的线程数量，以保证多个线程能够合理的使用特定资源。
 * Semaphore 就相当于一个许可证，线程需要先通过 acquire 方法获取该许可证，该线程才能继续往下执行，否则只能在该方法出阻塞等待。
 * 当执行完业务功能后，需要通过release()方法将许可证归还，以便其他线程能够获得许可证继续执行。
 * <p>
 * 场景：全班30个学生用5个体重计测量体重
 *
 * @author cakeralter
 * @date 2020/4/21
 */
public class SemaphoreDemo {

    private static Semaphore weightScale = new Semaphore(5);
    private final static int STUDENTS = 30;
    private final static SplittableRandom random = new SplittableRandom();

    public static void main(String[] args) {
        for (int i = 1; i <= STUDENTS; i++) {
            new Thread(() -> {
                try {
                    weightScale.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("同学" + Thread.currentThread().getName() + "正在测量体重...");
                try {
                    Thread.sleep(random.split().nextInt(10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("同学" + Thread.currentThread().getName() + "体重测量完毕");
                weightScale.release();
            }, String.valueOf(i)).start();
        }
    }
}
