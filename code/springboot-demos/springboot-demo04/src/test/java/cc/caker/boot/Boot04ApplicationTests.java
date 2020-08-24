package cc.caker.boot;

import cc.caker.boot.component.ProcessReceiver;
import cc.caker.boot.config.RabbitMqConfig;
import cc.caker.boot.util.ExpirationMessagePostProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class Boot04ApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 延迟消费场景 ttl设置在消息上
     *
     * @throws InterruptedException
     */
    @Test
    public void testDelayQueuePerMessageTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            long expiration = i * 1000;
            rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
                    (Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration),
                    new ExpirationMessagePostProcessor(expiration));
        }
        ProcessReceiver.latch.await();
    }

    /**
     * 延迟消费场景 ttl设置在队列上
     *
     * @throws InterruptedException
     */
    @Test
    public void testDelayQueuePerQueueTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME,
                    "Message From delay_queue_per_queue_ttl with expiration " + RabbitMqConfig.QUEUE_EXPIRATION);
        }
        ProcessReceiver.latch.await();
    }

    /**
     * 延迟重试场景
     *
     * @throws InterruptedException
     */
    @Test
    public void testFailMessage() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(6);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_PROCESS_QUEUE_NAME, ProcessReceiver.FAIL_MESSAGE);
        }
        ProcessReceiver.latch.await();
    }
}
