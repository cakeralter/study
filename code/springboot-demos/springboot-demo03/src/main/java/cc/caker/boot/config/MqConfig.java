package cc.caker.boot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
@Configuration
public class MqConfig {

    @Bean
    public Queue delCacheQueue() {
        return new Queue("del-cache-queue");
    }
}
