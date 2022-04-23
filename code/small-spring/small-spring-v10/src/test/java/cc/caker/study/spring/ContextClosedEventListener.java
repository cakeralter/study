package cc.caker.study.spring;

import cc.caker.study.spring.context.ApplicationListener;
import cc.caker.study.spring.context.event.ContextClosedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * ContextClosedEventListener
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
@Slf4j
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("关闭事件：{}", this.getClass().getName());
    }
}
