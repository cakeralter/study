package cc.caker.study.spring.beans.factory.support;

import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * CglibSubclassingInstantiationStrategy
 * Cglib实例化
 *
 * @author cakeralter
 * @date 2022/4/13
 * @since 1.0
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    /**
     * instantiate
     *
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object... args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (constructor != null) {
            return enhancer.create(constructor.getParameterTypes(), args);
        }
        return enhancer.create();
    }
}
