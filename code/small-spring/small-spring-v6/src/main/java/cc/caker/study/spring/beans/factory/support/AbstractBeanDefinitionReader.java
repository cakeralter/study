package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.core.io.DefaultResourceLoader;
import cc.caker.study.spring.core.io.ResourceLoader;

/**
 * AbstractBeanDefinitionReader
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
