package cc.caker.study.spring.aop.framework;

import cc.caker.study.spring.aop.AdvisedSupport;
import lombok.RequiredArgsConstructor;

/**
 * ProxyFactory
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
@RequiredArgsConstructor
public class ProxyFactory {

    private final AdvisedSupport advisedSupport;

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
