package cc.caker.boot.component.limiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 请求限流
 *
 * @author cakeralter
 * @date 2020/8/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimiter {

    /**
     * 每秒创建令牌数
     *
     * @return
     */
    double qps() default 10d;

    /**
     * 超时等待
     *
     * @return
     */
    long timeout() default 5000;

    TimeUnit unit() default TimeUnit.MILLISECONDS;

    /**
     * 限流提示
     *
     * @return
     */
    String message() default "你的脚步太快,服务器跟不上啦!";
}
