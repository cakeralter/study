package cc.caker.study.spring.context.event;

import cc.caker.study.spring.context.ApplicationContext;
import cc.caker.study.spring.context.ApplicationEvent;

/**
 * ApplicationContextEvent
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
