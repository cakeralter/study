package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * createBean
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object instance = null;
        try {
            instance = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failedInstantiation of bean failed", e);
        }

        addSingleton(beanName, instance);
        return instance;
    }
}
