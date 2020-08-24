package cc.caker.boot.component;

import cc.caker.boot.config.QueueConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangyl
 * @date 2019/8/13
 * @description 队列消费者
 */
@RabbitListener(queues = QueueConfig.QUEUE_NAME)
@Component
public class QueueConsumer {

    @Autowired
    AmqpTemplate template;

    /**
     * 消息消费
     *
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void receive(String msg) {
        System.out.println(Thread.currentThread().getName() + " receive message: " + msg);
    }
}

@RabbitListener(queues = "queue.a")
@Component
class QueueConsumerA {

    @Autowired
    AmqpTemplate template;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("[queue.a] receive message: " + msg);
    }
}

@RabbitListener(queues = "queue.b")
@Component
class QueueConsumerB {

    @Autowired
    AmqpTemplate template;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("[queue.b] receive message: " + msg);
    }
}

@RabbitListener(queues = "queue.c")
@Component
class QueueConsumerC {

    @Autowired
    AmqpTemplate template;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("[queue.c] receive message: " + msg);
    }
}

@RabbitListener(queues = "topic.a")
@Component
class TopicConsumerA {

    @Autowired
    AmqpTemplate template;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("[topic.a] receive message: " + msg);
    }
}

@RabbitListener(queues = "topic.b")
@Component
class TopicConsumerB {

    @Autowired
    AmqpTemplate template;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("[topic.b] receive message: " + msg);
    }
}

@RabbitListener(queues = "topic.c")
@Component
class TopicConsumerC {

    @Autowired
    AmqpTemplate template;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("[topic.c] receive message: " + msg);
    }
}
