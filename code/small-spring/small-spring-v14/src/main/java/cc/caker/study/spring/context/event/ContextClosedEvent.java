package cc.caker.study.spring.context.event;

/**
 * ContextClosedEvent
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
