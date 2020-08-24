package cc.caker.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyl
 * @date 2019/8/13
 * @description fanout队列模式
 */
@Configuration
public class FanoutQueueConfig {

    /**
     * 定义队列
     *
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue("queue.a");
    }

    @Bean
    public Queue queueB() {
        return new Queue("queue.b");
    }

    @Bean
    public Queue queueC() {
        return new Queue("queue.c");
    }

    /**
     * 定义交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange("queueExchange");
    }

    /**
     * 队列绑定到交换机
     *
     * @return
     */
    @Bean
    public Binding bindingExchangeWithA() {
        return BindingBuilder.bind(queueA()).to(exchange());
    }

    @Bean
    public Binding bindingExchangeWithB() {
        return BindingBuilder.bind(queueB()).to(exchange());
    }

    @Bean
    public Binding bindingExchangeWithC() {
        return BindingBuilder.bind(queueC()).to(exchange());
    }
}
