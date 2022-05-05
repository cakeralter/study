package cc.caker.study.spring.beans.factory.config;

/**
 * SingletonBeanRegistry
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
