package cc.caker.study.spring.context.annotation;

import java.lang.annotation.*;

/**
 * Scope
 *
 * @author cakeralter
 * @date 2022/5/8
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
