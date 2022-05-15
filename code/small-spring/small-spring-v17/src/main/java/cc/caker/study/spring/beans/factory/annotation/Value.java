package cc.caker.study.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Value
 *
 * @author cakeralter
 * @date 2022/5/8
 * @since 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    String value();
}
