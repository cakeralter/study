package cc.caker.study.spring.context;

/**
 * ApplicationEventPublisher
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
