package cc.caker.boot;

import cc.caker.boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootTest
class Boot03ApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Map<String, UserService> beansOfType = applicationContext.getBeansOfType(UserService.class);
        beansOfType.entrySet().forEach(System.out::println);
    }
}
