package cc.caker.study.spring;

import cc.caker.study.spring.beans.PropertyValue;
import cc.caker.study.spring.beans.PropertyValues;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.beans.factory.config.BeanReference;
import cc.caker.study.spring.beans.factory.support.DefaultListableBeanFactory;
import cc.caker.study.spring.dao.UserDao;
import cc.caker.study.spring.service.UserService;
import org.junit.Test;

/**
 * SpringUtilTest
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public class SpringUnitTest {

    /**
     * 测试Bean属性设置
     */
    @Test
    public void testApplyPropertyValue() {
        // 准备容器环境
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
    }
}
