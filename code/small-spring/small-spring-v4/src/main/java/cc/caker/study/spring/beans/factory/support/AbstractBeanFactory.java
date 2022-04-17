package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.BeanFactory;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;

/**
 * AbstractBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * getBean
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    /**
     * getBean
     *
     * @param beanName
     * @param args
     * @return
     */
    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    /**
     * doGetBean
     *
     * @param beanName
     * @param args
     * @return
     */
    protected <T> T doGetBean(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        /* 已加载直接返回 */
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }


    /**
     * getBeanDefinition
     *
     * @param beanName
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * createBean
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args);
}
