package top.caker.thread;

/**
 * 多线程顺序执行
 * <p>
 * 场景：10000张票由5个线程顺序购买
 *
 * @author cakeralter
 * @date 2020/4/21
 */
public class SequentialExecution {

    private static int ticket = 10000;
    private final static int LENGTH = 5;
    private final static Object lock = new Object();
    private static int run = 0;

    private static class Consumer implements Runnable {
        private final int id;

        public Consumer(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (run % LENGTH != id) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (ticket <= 0) {
                        break;
                    }
                    System.out.println("线程" + id + "卖掉" + ticket-- + ", 剩" + ticket);
                    run++;
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < LENGTH; i++) {
            new Thread(new Consumer(i)).start();
        }
    }
}
