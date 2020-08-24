package cc.caker.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyl
 * @date 2019/8/13
 * @description 普通工作队列模式
 */
@Configuration
public class QueueConfig {

    public final static String QUEUE_NAME = "QUEUR";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }
}
