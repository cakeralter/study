package cc.caker.study.spring.context.event;

import cc.caker.study.spring.context.ApplicationEvent;
import cc.caker.study.spring.context.ApplicationListener;

/**
 * ApplicationEventMulticaster
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public interface ApplicationEventMulticaster {

    /**
     * 注册监听
     *
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除监听
     *
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     *
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
