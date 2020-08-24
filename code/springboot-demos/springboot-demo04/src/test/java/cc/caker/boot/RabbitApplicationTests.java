package cc.caker.boot;

import cc.caker.boot.component.QueueProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author cakeralter
 * @date 2020/8/24
 */
@SpringBootTest
public class RabbitApplicationTests {

    @Autowired
    private QueueProducer producer;

    @Test
    public void testQueue() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            producer.send();
            Thread.sleep(2000);
        }
    }

    @Test
    public void testFanoutQueue() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            producer.fanoutSend();
            Thread.sleep(2000);
        }
    }

    @Test
    public void testTopicQueue() {
        producer.topicSendA();
        producer.topicSendB();
        producer.topicSendC();
    }
}
