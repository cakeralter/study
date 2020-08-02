package top.caker.demos.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

/**
 * 自定义注解
 * <p>
 * 元注解：
 * <b>@Retention ：定义作用域<b/>
 * <b>@Target ：定义可以在哪些地方使用</b>
 * <b>@Inherited ：定义可被继承</b>
 * <b>@Documented ：定义可被文档解析</b>
 * <b>@Repeatable ：定义可重复使用<b/>
 *
 * @author cakeralter
 * @date 2020/8/2
 */
@Repeatable(Inits.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
@Inherited
public @interface Init {

    String name();

    int age();

    String grade();

    String value() default "";
}
