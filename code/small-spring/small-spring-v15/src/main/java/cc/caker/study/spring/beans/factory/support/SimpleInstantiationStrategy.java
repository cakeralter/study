package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * SimpleInstantiationStrategy
 * JDK代理实例化
 *
 * @author cakeralter
 * @date 2022/4/13
 * @since 1.0
 */
@Slf4j
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object... args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (constructor != null) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", ex);
        }
    }
}
