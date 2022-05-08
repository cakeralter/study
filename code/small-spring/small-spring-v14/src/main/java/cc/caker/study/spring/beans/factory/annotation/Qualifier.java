package cc.caker.study.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Qualifier
 *
 * @author cakeralter
 * @date 2022/5/8
 * @since 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

    String value() default "";
}
