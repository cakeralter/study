package cc.caker.study.spring;

import cc.caker.study.spring.beans.factory.support.DefaultListableBeanFactory;
import cc.caker.study.spring.beans.factory.xml.XmlBeanDefinitionReader;
import cc.caker.study.spring.component.CustomBeanFactoryPostProcessor;
import cc.caker.study.spring.component.CustomBeanPostProcessor;
import cc.caker.study.spring.context.support.ClassPathXmlApplicationContext;
import cc.caker.study.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * SpringUnitTest
 *
 * @author cakeralter
 * @date 2022/4/18
 * @since 1.0
 */
@Slf4j
public class SpringUnitTest {

    @Test
    public void testNoContext() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-beans.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        CustomBeanPostProcessor beanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String userInfo = userService.getUserInfo();
        log.info("查询用户信息：" + userInfo);
    }

    @Test
    public void testRefresh() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-beans.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.getUserInfo();
        log.info("查询用户信息：" + userInfo);
    }
}
