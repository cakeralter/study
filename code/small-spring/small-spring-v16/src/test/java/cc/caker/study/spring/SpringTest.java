package cc.caker.study.spring;

import cc.caker.study.spring.bean.Husband;
import cc.caker.study.spring.bean.Wife;
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
    public void testCircle() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        log.info("老公的媳妇：" + husband.queryWife());
        log.info("媳妇的老公：" + wife.queryHusband());
    }
}
