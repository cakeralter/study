package cc.caker.boot.component;

import cc.caker.common.service.RedisService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

import static cc.caker.boot.constant.RedisConst.*;

/**
 * app生命周期事件
 *
 * @author cakeralter
 * @date 2020/8/13
 */
@RequiredArgsConstructor
@Configuration
public class LifeCycleListener {

    private final RedisService redisService;

    /**
     * 关闭app时清空redis权限缓存
     */
    @EventListener(ContextClosedEvent.class)
    public void closed() {
        List<String> keys =
                Lists.newArrayList(UM_ADMIN_ROLE, UM_ROLE_RESOURCE, UM_ADMIN_ROLE, UM_RESOURCES_ALL, UM_RESOURCES_ENABLED_ALL);
        redisService.delete(keys.toArray(new String[0]));
    }
}
