package cc.caker.study.spring.bean;

import cc.caker.study.spring.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * HusbandMother
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public class HusbandMother implements FactoryBean<IMother> {

    @Override
    public IMother getObject() throws Exception {
        return (IMother) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IMother.class}, (proxy, method, args) -> "婚后媳妇妈妈的职责被婆婆代理了！" + method.getName());
    }

    @Override
    public Class<?> getObjectType() {
        return IMother.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
