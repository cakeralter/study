package cc.caker.study.spring.beans.factory.support;

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
    protected void createBean(String beanName, BeanDefinition beanDefinition) {

    }
}
