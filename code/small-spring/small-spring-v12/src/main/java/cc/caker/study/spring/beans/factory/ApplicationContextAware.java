package cc.caker.study.spring.beans.factory;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.context.ApplicationContext;

/**
 * ApplicationContextAware
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
