package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.DisposableBean;
import cc.caker.study.spring.beans.factory.config.SingletonBeanRegistry;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * DefaultSingletonBeanRegistry
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();
    /**
     * singletonObjects
     */
    private final Map<String, Object> singletonObjects = Maps.newHashMap();
    /**
     * disposableBeans
     */
    private final Map<String, DisposableBean> disposableBeans = Maps.newHashMap();

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加单例对象
     *
     * @param beanName
     * @param singletonObject
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    /**
     * 注册disposableBean
     *
     * @param beanName
     * @param bean
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    /**
     * destroy
     */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
