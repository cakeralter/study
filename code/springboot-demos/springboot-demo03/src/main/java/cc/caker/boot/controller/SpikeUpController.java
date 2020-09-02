package cc.caker.boot.controller;

import cc.caker.boot.constant.RedisEnum;
import cc.caker.boot.repo.entity.Order;
import cc.caker.boot.service.impl.SpikeUpServiceImpl;
import cc.caker.common.vo.ResponseResult;
import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 秒杀升级
 *
 * @author cakeralter
 * @date 2020/9/1
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class SpikeUpController {

    private final RateLimiter limiter = RateLimiter.create(10d);
    private final SpikeUpServiceImpl spikeUpService;
    private final RedisTemplate<String, String> redisTemplate;
    private Executor executor = Executors.newScheduledThreadPool(5);
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/place/up/{uid}/{sid}")
    public ResponseResult<Order> placeOrderRate(@PathVariable Long uid, @PathVariable Long sid,
                                                String hash) {
        if (!limiter.tryAcquire()) {
            throw new RuntimeException("你的请求太快，服务器跟不上你的节奏了！");
        }
        String key = String.format("%s::%d", RedisEnum.STOCK_CACHE_KEY.getKey(), sid);
        // 删除缓存
        redisTemplate.delete(key);
        log.info("删除缓存：[{}]", key);
        Order order = spikeUpService.place(uid, sid, hash);
        // 延时双删
        /*executor.execute(() -> {
            try {
                redisTemplate.delete(key);
                Thread.sleep(1000);
                log.info("延时删除缓存：[{}]", key);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        });*/
        // 消息队列删除
        rabbitTemplate.convertAndSend("del-cache-queue", key);
        return ResponseResult.ok(order);
    }
}
