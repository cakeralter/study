package cc.caker.study.spring.beans.factory;

import cc.caker.study.spring.beans.BeansException;

/**
 * BeanFactoryAware
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
