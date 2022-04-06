package cc.caker.study.spring.beans.factory.config;

/**
 * SingletonObjectRegistry
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public interface SingletonObjectRegistry {

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
