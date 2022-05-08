package cc.caker.study.spring.context.event;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.BeanFactory;
import cc.caker.study.spring.beans.factory.BeanFactoryAware;
import cc.caker.study.spring.context.ApplicationEvent;
import cc.caker.study.spring.context.ApplicationListener;
import cc.caker.study.spring.uitl.ClassUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * AbstractApplicationEventMulticaster
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = Sets.newLinkedHashSet();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        this.applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        this.applicationListeners.remove((ApplicationListener<ApplicationEvent>) listener);
    }

    /**
     * 获取匹配事件的监听器
     *
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        List<ApplicationListener> matchingListeners = Lists.newLinkedList();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                matchingListeners.add(listener);
            }
        }
        return matchingListeners;
    }

    /**
     * 判断是否匹配
     *
     * @param listener
     * @param event
     * @return
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，默认所有的类的终极父类都是Object。
        // 如果A.isAssignableFrom(B)结果是true，证明B可以转换成为A,也就是A可以由B转换而来。
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
