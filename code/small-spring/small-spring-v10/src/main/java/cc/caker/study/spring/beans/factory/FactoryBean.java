package cc.caker.study.spring.beans.factory;

/**
 * FactoryBean
 *
 * @author cakeralter
 * @date 2022/4/21
 * @since 1.0
 */
public interface FactoryBean<T> {

    /**
     * 获取实例对象
     *
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取对象类型
     *
     * @return
     */
    Class<?> getObjectType();

    /**
     * 判断是否为单例
     *
     * @return
     */
    boolean isSingleton();
}
