package cc.caker.boot.componet;

import cc.caker.boot.config.RedisConfig;
import cc.caker.boot.constant.RedisEnum;
import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.service.impl.SpikeUpServiceImpl;
import cc.caker.boot.vo.OrderMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
@RequiredArgsConstructor
@Slf4j
@RabbitListener(queues = "order-queue")
@Component
public class OrderListener {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final SpikeUpServiceImpl spikeUpService;

    @RabbitHandler
    public void delCache(String message) throws JsonProcessingException {
        OrderMessage om = RedisConfig.getMapper().readValue(message, OrderMessage.class);
        log.info("接收到队列消息：[{}]", message);
        String key = String.format("%s::%d", RedisEnum.STOCK_CACHE_KEY.getKey(), om.getSid());
        // 删除缓存
        redisTemplate.delete(key);
        log.info("删除缓存：[{}]", key);
        log.info("开始执行下单业务：[{}]", om);
        Order order = spikeUpService.place(om.getUid(), om.getSid(), om.getHash());
        log.info("[{}]成功购买[{}]！", order.getUserId(), order.getName());
        // 消息队列删除
        rabbitTemplate.convertAndSend("del-cache-queue", key);
    }
}
