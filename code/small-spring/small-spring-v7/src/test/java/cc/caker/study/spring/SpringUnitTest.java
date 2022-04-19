package cc.caker.study.spring;

import cc.caker.study.spring.context.support.ClassPathXmlApplicationContext;
import cc.caker.study.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * SpringUnitTest
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
@Slf4j
public class SpringUnitTest {

    @Test
    public void test() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-beans.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        log.info("{}", result);
    }
}
