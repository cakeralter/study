package cc.caker.study.spring.beans.factory;

import cc.caker.study.spring.beans.BeansException;

/**
 * BeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public interface BeanFactory {

    /**
     * 获取Bean实例
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 获取Bean实例
     *
     * @param beanName
     * @param args
     * @return
     */
    Object getBean(String beanName, Object... args) throws BeansException;

    /**
     * getBean
     *
     * @param beanName
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;
}
