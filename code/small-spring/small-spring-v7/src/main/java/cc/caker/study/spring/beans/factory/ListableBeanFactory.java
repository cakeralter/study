package cc.caker.study.spring.beans.factory;

import cc.caker.study.spring.beans.BeansException;

import java.util.Map;

/**
 * ListableBeanFactory
 * 扩展BeanFactory
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException;

    /**
     * 获取所有BeanDefinition名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
