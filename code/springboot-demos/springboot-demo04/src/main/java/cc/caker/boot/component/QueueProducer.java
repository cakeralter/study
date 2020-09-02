package cc.caker.boot.component;

import cc.caker.boot.config.QueueConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wangyl
 * @date 2019/8/13
 * @description 生产者
 */
@Component
public class QueueProducer {

    @Autowired
    AmqpTemplate template;

    /**
     * 普通队列模式生产消息
     */
    public void send() {
        String content = Thread.currentThread().getName() + " send message: " + LocalDateTime.now();
        template.convertAndSend(QueueConfig.QUEUE_NAME, content);
    }

    /**
     * fanout模式生产消息
     */
    public void fanoutSend() {
        String content = "FanoutProducer send message: " + LocalDateTime.now();
        // 第一个参数是我们交换机的名称 第二个参数是routerKey 第三个是你要发送的消息
        template.convertAndSend("queueExchange", "", content);
    }

    public void topicSendA() {
        String content = "[topic.msg] send topic.msg: " + LocalDateTime.now();
        template.convertAndSend("topicExchange", "topic.msg", content);
    }

    public void topicSendB() {
        String content = "[topic.good.msg] send topic.#: " + LocalDateTime.now();
        template.convertAndSend("topicExchange", "topic.good.msg", content);
    }

    public void topicSendC() {
        String content = "[topic.message.z] send topic.*.z: " + LocalDateTime.now();
        template.convertAndSend("topicExchange", "topic.message.z", content);
    }
}
