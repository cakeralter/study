package cc.caker.study.spring;

import cc.caker.study.spring.beans.factory.support.DefaultListableBeanFactory;
import cc.caker.study.spring.beans.factory.xml.XmlBeanDefinitionReader;
import cc.caker.study.spring.core.io.DefaultResourceLoader;
import cc.caker.study.spring.core.io.Resource;
import cc.caker.study.spring.service.UserService;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * SpringUnitTest
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
@Slf4j
public class SpringUnitTest {

    private DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * 测试加载资源文件
     */
    @Test
    public void testLoadResource() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        InputStream is = resource.getInputStream();
        String content = IoUtil.read(is, Charset.defaultCharset());
        log.info("load by classpath, content = {}", content);

        resource = resourceLoader.getResource("/Users/cakeralter/Workspace/study/code/small-spring/small-spring-v5/src/main/resources/application.properties");
        is = resource.getInputStream();
        content = IoUtil.read(is, Charset.defaultCharset());
        log.info("load by file system, content = {}", content);

        resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        is = resource.getInputStream();
        content = IoUtil.read(is, Charset.defaultCharset());
        log.info("load by url, content = {}", content);
    }

    /**
     * 测试注册Bean
     */
    @Test
    public void testRegisterBeanByResource() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring-beans.xml");

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.getUserInfo();
    }
}
