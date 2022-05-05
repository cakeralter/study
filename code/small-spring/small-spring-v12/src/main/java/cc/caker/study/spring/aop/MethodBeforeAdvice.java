package cc.caker.study.spring.aop;

import java.lang.reflect.Method;

/**
 * MethodBeforeAdvice
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * 前置
     *
     * @param method
     * @param args
     * @param target
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
