package cc.caker.study.spring.context;

import cc.caker.study.spring.beans.BeansException;

/**
 * ConfigurableApplicationContext
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
