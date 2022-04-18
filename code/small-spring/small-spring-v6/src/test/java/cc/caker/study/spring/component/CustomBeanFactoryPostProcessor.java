package cc.caker.study.spring.component;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.PropertyValue;
import cc.caker.study.spring.beans.PropertyValues;
import cc.caker.study.spring.beans.factory.ConfigurableListableBeanFactory;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * CustomBeanFactoryPostProcessor
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("sex", "女"));
        propertyValues.addPropertyValue(new PropertyValue("age", "25"));
    }
}
