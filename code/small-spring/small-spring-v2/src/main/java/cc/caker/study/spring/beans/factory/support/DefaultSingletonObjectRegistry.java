package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.config.SingletonObjectRegistry;

/**
 * DefaultSingletonObjectRegistry
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class DefaultSingletonObjectRegistry implements SingletonObjectRegistry {

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return null;
    }
}
