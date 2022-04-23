package cc.caker.study.spring;

import cc.caker.study.spring.context.ApplicationListener;
import cc.caker.study.spring.context.event.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * ContextRefreshedEventListener
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
@Slf4j
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("刷新事件：{}", this.getClass().getName());
    }
}
