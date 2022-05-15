package cc.caker.study.spring.context;

import cc.caker.study.spring.beans.factory.HierarchicalBeanFactory;
import cc.caker.study.spring.beans.factory.ListableBeanFactory;
import cc.caker.study.spring.core.io.ResourceLoader;

/**
 * ApplicationContext
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
