package top.caker.demos.queue;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.SplittableRandom;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author cakeralter
 * @date 2020/5/11
 */
public class DelayQueueTest {

    private final static int LENGTH = 10;
    private static DelayQueue<Order> orders = new DelayQueue<>();
    private final static SplittableRandom RAND = new SplittableRandom();

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < LENGTH; i++) {
            orders.add(new Order(RAND.nextInt(100), TimeUnit.SECONDS));
        }

        while (true) {
            Order order = orders.poll();
            if (Objects.nonNull(order)) {
                System.out.println(order);
                Thread.sleep(1000);
            }
            if (orders.isEmpty()) {
                break;
            }
        }
    }

    static class Order implements Delayed {
        private static int count = 1;
        private final int id;
        private long delay;

        public Order(long delay, TimeUnit unit) {
            this.delay = System.currentTimeMillis() + unit.toMillis(delay);
        }

        {
            id = count++;
        }

        public int getId() {
            return id;
        }

        public long getDelay() {
            return delay;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "id=" + id +
                    ", delay=" + LocalDateTime.ofInstant(Instant.ofEpochMilli(delay), ZoneId.systemDefault()) +
                    '}';
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return delay - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.delay, o.getDelay(null));
        }
    }
}
