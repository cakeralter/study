package cc.caker.study.spring;

import cc.caker.study.spring.beans.BeanDefinition;
import cc.caker.study.spring.beans.BeanFactory;
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

    private BeanFactory beanFactory;

    @Before
    public void before() {
        beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);
    }

    @Test
    public void test() {
        UserService userService = (UserService) beanFactory.getBean("userService");
        log.info(userService.getByName("张三"));
    }
}
