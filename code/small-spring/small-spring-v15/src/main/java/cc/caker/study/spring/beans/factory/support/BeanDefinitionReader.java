package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.core.io.Resource;
import cc.caker.study.spring.core.io.ResourceLoader;

/**
 * BeanDefinitionReader
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public interface BeanDefinitionReader {

    /**
     * getRegistry
     *
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * getResourceLoader
     *
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * loadBeanDefinitions
     *
     * @param resource
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * loadBeanDefinitions
     *
     * @param resources
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * loadBeanDefinitions
     *
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * loadBeanDefinitions
     *
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
