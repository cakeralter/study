package cc.caker.study.spring.stereotype;

import java.lang.annotation.*;

/**
 * Component
 *
 * @author cakeralter
 * @date 2022/5/8
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
