package cc.caker.boot.util;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @author wangyl
 * @date 2019/8/13
 * @description 设置队列消息的过期时间
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {

    private final Long ttl;

    public ExpirationMessagePostProcessor(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties()
                // 设置per-message的失效时间
                .setExpiration(ttl.toString());
        return message;
    }
}
