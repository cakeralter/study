package cc.caker.study.spring.beans.factory;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.config.AutowireCapableBeanFactory;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * ConfigurableListableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 获取BeanDefinition
     *
     * @param beanDefinitionName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanDefinitionName) throws BeansException;

    /**
     * 实例化单例前执行
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
