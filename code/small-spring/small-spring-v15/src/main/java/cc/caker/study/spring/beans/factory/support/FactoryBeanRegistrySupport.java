package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.FactoryBean;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * FactoryBeanRegistrySupport
 *
 * @author cakeralter
 * @date 2022/4/21
 * @since 1.0
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     */
    private final Map<String, Object> factoryBeanObjectCache = Maps.newConcurrentMap();

    protected Object getCachedObjectFromFactoryBean(String beanName) {
        Object object = factoryBeanObjectCache.get(beanName);
        return object != NULL_OBJECT ? object : null;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object object = factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factoryBean, beanName);
                factoryBeanObjectCache.put(beanName, object);
            }
            return object != NULL_OBJECT ? object : null;
        }

        return doGetObjectFromFactoryBean(factoryBean, beanName);
    }

    protected Object doGetObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
