package cc.caker.study.spring.context.event;

import cc.caker.study.spring.beans.factory.BeanFactory;
import cc.caker.study.spring.context.ApplicationEvent;
import cc.caker.study.spring.context.ApplicationListener;

/**
 * SimpleApplicationEventMulticaster
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
