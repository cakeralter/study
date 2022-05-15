package cc.caker.study.spring.aop.framework.adapter;

import cc.caker.study.spring.aop.MethodBeforeAdvice;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * MethodBeforeAdviceInterceptor
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
