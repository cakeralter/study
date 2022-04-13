package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /***
     * 实例化策略
     */
    private final InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * createBean
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object instance = null;
        try {
            instance = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failedInstantiation of bean failed", e);
        }

        addSingleton(beanName, instance);
        return instance;
    }

    /**
     * createBean
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) {
        Constructor constructor = null;
        if (args != null && args.length > 0) {
            // 若参数不为空则找出和参数个数相同的构造器
            Constructor[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();
            for (Constructor declaredConstructor : declaredConstructors) {
                if (declaredConstructor.getParameterCount() == args.length) {
                    constructor = declaredConstructor;
                    break;
                }
            }
        }

        Object instance = instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
        addSingleton(beanName, instance);
        return instance;
    }
}
