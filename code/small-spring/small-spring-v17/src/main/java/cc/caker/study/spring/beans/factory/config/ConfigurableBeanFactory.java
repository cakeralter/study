package cc.caker.study.spring.beans.factory.config;

import cc.caker.study.spring.beans.factory.HierarchicalBeanFactory;
import cc.caker.study.spring.core.convert.ConversionService;
import cc.caker.study.spring.uitl.StringValueResolver;

/**
 * ConfigurableBeanFactory
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    ConversionService getConversionService();

    void setConversionService(ConversionService conversionService);
}
