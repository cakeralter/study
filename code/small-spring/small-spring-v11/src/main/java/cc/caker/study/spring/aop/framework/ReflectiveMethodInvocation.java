package cc.caker.study.spring.aop.framework;

import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * ReflectiveMethodInvocation
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@RequiredArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected final Object target;
    protected final Method method;
    protected final Object[] arguments;

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
