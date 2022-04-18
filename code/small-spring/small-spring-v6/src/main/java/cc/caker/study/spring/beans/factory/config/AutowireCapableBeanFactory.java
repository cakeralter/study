package cc.caker.study.spring.beans.factory.config;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.BeanFactory;

/**
 * AutowireCapableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
