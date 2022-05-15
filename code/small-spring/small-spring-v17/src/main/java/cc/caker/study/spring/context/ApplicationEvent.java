package cc.caker.study.spring.context;

import java.util.EventObject;

/**
 * ApplicationEvent
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
