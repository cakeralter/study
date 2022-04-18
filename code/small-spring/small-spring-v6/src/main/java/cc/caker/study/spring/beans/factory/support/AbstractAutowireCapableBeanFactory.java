package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.PropertyValue;
import cc.caker.study.spring.beans.PropertyValues;
import cc.caker.study.spring.beans.factory.config.AutowireCapableBeanFactory;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.beans.factory.config.BeanPostProcessor;
import cc.caker.study.spring.beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Constructor;

/**
 * AbstractAutowireCapableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /***
     * 实例化策略
     */
    private final InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

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
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 设置bean属性
            applyPropertyValue(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // TODO: invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {
        // TODO
    }

    /**
     * createBeanInstance
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object... args) {
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

        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
    }

    /**
     * 设置bean属性
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference beanReference) {
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    /**
     * 前置处理
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 后置处理
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
