package top.caker.demos.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author cakeralter
 * @date 2020/8/2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
@Inherited
public @interface Inits {

    Init[] value();
}
