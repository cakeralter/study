package cc.caker.study.spring.context.support;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.ApplicationContextAware;
import cc.caker.study.spring.beans.factory.config.BeanPostProcessor;
import cc.caker.study.spring.context.ApplicationContext;
import lombok.RequiredArgsConstructor;

/**
 * ApplicationContextAwareProcessor
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
@RequiredArgsConstructor
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
