package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * InstantiationStrategy
 *
 * @author cakeralter
 * @date 2022/4/13
 * @since 1.0
 */
public interface InstantiationStrategy {

    /**
     * instantiate
     *
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object... args);
}
