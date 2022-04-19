package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.beans.factory.config.BeanPostProcessor;
import cc.caker.study.spring.beans.factory.config.ConfigurableBeanFactory;
import cc.caker.study.spring.uitl.ClassUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * AbstractBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();
    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * getBean
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    /**
     * getBean
     *
     * @param beanName
     * @param args
     * @return
     */
    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    /**
     * doGetBean
     *
     * @param beanName
     * @param args
     * @return
     */
    protected <T> T doGetBean(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        /* 已加载直接返回 */
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }


    /**
     * getBeanDefinition
     *
     * @param beanName
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * createBean
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
