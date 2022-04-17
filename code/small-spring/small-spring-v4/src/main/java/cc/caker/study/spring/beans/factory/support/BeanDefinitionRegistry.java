package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinitionRegistry
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public interface BeanDefinitionRegistry {

    /**
     * registerBeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
