package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * DefaultListableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    /**
     * beanDefinitionMap
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = Maps.newHashMap();
    private BeanDefinition beanDefinition;

    /**
     * 获取Bean定义
     *
     * @param beanName
     * @return
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    /**
     * registerBeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }
}
