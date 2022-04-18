package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.config.SingletonBeanRegistry;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * DefaultSingletonBeanRegistry
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * singletonObjects
     */
    private final Map<String, Object> singletonObjects = Maps.newHashMap();

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加单例对象
     *
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
