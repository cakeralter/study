package cc.caker.study.spring;

import cc.caker.study.spring.aop.MethodBeforeAdvice;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * UserServiceBeforeAdvice
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
@Slf4j
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.info("拦截方法：" + method.getName());
    }
}
