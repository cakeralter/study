package cc.caker.study.spring.beans.factory.config;

import cc.caker.study.spring.beans.BeansException;

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
}
