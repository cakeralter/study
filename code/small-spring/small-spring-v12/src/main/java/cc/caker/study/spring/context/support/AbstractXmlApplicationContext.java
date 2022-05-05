package cc.caker.study.spring.context.support;

import cc.caker.study.spring.beans.factory.support.DefaultListableBeanFactory;
import cc.caker.study.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * AbstractXmlApplicationContext
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            reader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
