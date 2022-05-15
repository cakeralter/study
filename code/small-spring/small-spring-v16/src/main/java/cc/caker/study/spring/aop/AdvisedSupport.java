package cc.caker.study.spring.aop;

import lombok.Getter;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * AdvisedSupport
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@Setter
@Getter
public class AdvisedSupport {

    /**
     * 目标对象
     */
    private TargetSource targetSource;
    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;
    /**
     * 匹配器
     */
    private MethodMatcher methodMatcher;

    private boolean proxyTargetClass = false;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }
}
