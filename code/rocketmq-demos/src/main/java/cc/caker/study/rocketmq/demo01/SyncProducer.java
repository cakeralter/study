package cc.caker.study.rocketmq.demo01;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * 发送同步消息
 * <p>
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
@Slf4j
public class SyncProducer {

    @SneakyThrows
    public static void main(String[] args) {
        // 1.实例化Producer
        DefaultMQProducer producer = new DefaultMQProducer("mq-sync-producer-group");
        // 2.设置NameServer
        producer.setNamesrvAddr("139.155.183.87:9876");
        // 3.启动实例
        producer.start();
        // 4.发送同步消息
        for (int i = 0; i < 10; i++) {
            String body = "This is a MQ message, index = " + i;
            Message msg = new Message("topic_test_sync", "Tag-A", body.getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = producer.send(msg);
            log.info("send result = {}", sendResult);
        }
        // 5.关闭实例
        producer.shutdown();
    }
}
