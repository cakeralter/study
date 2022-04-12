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
        Object bean = getSingleton(beanName);
        /* 已加载直接返回 */
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
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
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
