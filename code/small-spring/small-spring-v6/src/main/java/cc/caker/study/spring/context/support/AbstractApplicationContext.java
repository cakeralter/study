package cc.caker.study.spring.context.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.ConfigurableListableBeanFactory;
import cc.caker.study.spring.beans.factory.config.BeanFactoryPostProcessor;
import cc.caker.study.spring.beans.factory.config.BeanPostProcessor;
import cc.caker.study.spring.context.ConfigurableApplicationContext;
import cc.caker.study.spring.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * AbstractApplicationContext
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void refresh() throws BeansException {
        // 1.创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2.获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.实例化前执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4.注册BeanPostProcessor
        registerBeanPostProcessors(beanFactory);

        // 5.实例化单例Bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 注册BeanPostProcessor
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        beanPostProcessorMap.forEach((k, v) -> beanFactory.addBeanPostProcessor(v));
    }

    /**
     * 实例化前执行BeanFactoryPostProcessor
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        beanFactoryPostProcessorMap.forEach((k, v) -> v.postProcessBeanFactory(beanFactory));
    }

    /**
     * 获取容器
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;
}
