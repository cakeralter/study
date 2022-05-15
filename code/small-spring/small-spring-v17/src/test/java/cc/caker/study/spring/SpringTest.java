package cc.caker.study.spring;

import cc.caker.study.spring.bean.Husband;
import cc.caker.study.spring.context.support.ClassPathXmlApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * SpringTest
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
@Slf4j
public class SpringTest {

    @Test
    public void testStringToLocalDateConvert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        log.info("测试结果：" + husband);
    }
}
