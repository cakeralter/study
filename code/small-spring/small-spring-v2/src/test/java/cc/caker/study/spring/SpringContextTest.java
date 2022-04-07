package cc.caker.study.spring;

import cc.caker.study.spring.beans.UserService;
import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.beans.factory.support.DefaultListableBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * SpringContextTest
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
@Slf4j
public class SpringContextTest {

    private final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    /**
     * init
     */
    @Before
    public void init() {
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
    }

    /**
     * test
     */
    @Test
    public void test() {
        UserService userService_1 = (UserService) beanFactory.getBean("userService");
        userService_1.printUserInfo("林文韵");

        UserService userService_2 = (UserService) beanFactory.getBean("userService");
        log.info("{}", userService_1.equals(userService_2));
    }
}
