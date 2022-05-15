package cc.caker.study.spring.beans.factory.config;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.PropertyValues;

/**
 * InstantiationAwareBeanPostProcessor
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象实例化之前，执行此方法
     *
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 在 Spring 中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     *
     * @param bean
     * @param beanName
     * @return
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
