package cc.caker.study.spring.context.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.ConfigurableListableBeanFactory;
import cc.caker.study.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * AbstractRefreshableApplicationContext
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建容器
     *
     * @return
     */
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 加载BeanDefinition
     *
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
