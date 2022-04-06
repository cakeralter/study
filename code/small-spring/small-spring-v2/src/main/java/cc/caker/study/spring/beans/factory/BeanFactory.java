package cc.caker.study.spring.beans.factory;

/**
 * BeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public interface BeanFactory {

    /**
     * 获取Bean实例
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
