package cc.caker.study.spring.beans.factory;

import cc.caker.study.spring.beans.BeansException;

/**
 * ObjectFactory
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public interface ObjectFactory<T> {

    /**
     * 获取对象
     *
     * @return
     * @throws BeansException
     */
    T getObject() throws BeansException;
}
