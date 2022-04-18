package cc.caker.study.spring.component;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.config.BeanPostProcessor;
import cc.caker.study.spring.service.UserService;

/**
 * CustomBeanPostProcessor
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setUId("10002");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
