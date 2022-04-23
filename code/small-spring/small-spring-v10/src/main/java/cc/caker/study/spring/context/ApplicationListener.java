package cc.caker.study.spring.context;

import java.util.EventListener;

/**
 * ApplicationListener
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * handler
     *
     * @param event
     */
    void onApplicationEvent(E event);
}
