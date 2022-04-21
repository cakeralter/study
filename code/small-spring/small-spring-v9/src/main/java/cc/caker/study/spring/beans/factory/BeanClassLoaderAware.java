package cc.caker.study.spring.beans.factory;

/**
 * BeanClassLoaderAware
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
