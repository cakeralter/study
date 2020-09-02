package cc.caker.boot.componet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author cakeralter
 * @date 2020/9/2
 */
@Slf4j
@RequiredArgsConstructor
@Component
@RabbitListener(queues = "del-cache-queue")
public class DelCacheListener {

    @RabbitHandler
    public void delCache(String message) {
        log.info("接收到队列消息：[{}]", message);
        log.info("开始执行延时删除缓存：[{}]", message);

    }
}
