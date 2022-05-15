package cc.caker.study.spring.aop;

import java.lang.reflect.Method;

/**
 * MethodMatcher
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
