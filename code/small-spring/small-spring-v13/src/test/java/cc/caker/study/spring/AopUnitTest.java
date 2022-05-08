package cc.caker.study.spring;

import cc.caker.study.spring.context.support.ClassPathXmlApplicationContext;
import cc.caker.study.spring.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * AopUnitTest
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@Slf4j
public class AopUnitTest {

    @Test
    public void testProperty() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-beans.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        log.info("测试结果：" + userService);
    }

    @SneakyThrows
    @Test
    public void testScan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        log.info("测试结果：" + userService.queryUserInfo());
    }
}
