package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.DisposableBean;
import cc.caker.study.spring.beans.factory.ObjectFactory;
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
     * 一级缓存 普通对象
     */
    private final Map<String, Object> singletonObjects = Maps.newConcurrentMap();
    /**
     * 二级缓存 提前暴露的半成品对象
     */
    private final Map<String, Object> earlySingletonObjects = Maps.newHashMap();
    /**
     * 三级缓存 存放代理对象
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = Maps.newHashMap();
    /**
     * disposableBeans
     */
    private final Map<String, DisposableBean> disposableBeans = Maps.newLinkedHashMap();

    /**
     * 获取单例
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject == null) {
            // 一级缓存没有 尝试从二级缓存取
            singletonObject = earlySingletonObjects.get(beanName);
            if (singletonObject == null) {
                // 二级缓存没有 尝试从三级缓存取代理对象
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 放入二级缓存 移除三级缓存
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    /**
     * 注册单例对象
     *
     * @param beanName
     * @param singletonObject
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        // 移除二三级缓存
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    /**
     * 注册三级缓存
     *
     * @param beanName
     * @param singletonFactory
     */
    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!singletonObjects.containsKey(beanName)) {
            singletonFactories.put(beanName, singletonFactory);
            earlySingletonObjects.remove(beanName);
        }
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
