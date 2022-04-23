package cc.caker.study.spring;

import cc.caker.study.spring.context.event.ApplicationContextEvent;
import lombok.Getter;

/**
 * CustomEvent
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
@Getter
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }
}
