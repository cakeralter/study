package cc.caker.study.spring.beans;

/**
 * BeansException
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class BeansException extends RuntimeException {

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeansException(String message) {
        super(message);
    }
}
