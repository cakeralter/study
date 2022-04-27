package cc.caker.study.spring;

import cc.caker.study.spring.aop.AdvisedSupport;
import cc.caker.study.spring.aop.TargetSource;
import cc.caker.study.spring.aop.aspectj.AspectJExpressionPointcut;
import cc.caker.study.spring.aop.framework.Cglib2AopProxy;
import cc.caker.study.spring.aop.framework.JdkDynamicAopProxy;
import cc.caker.study.spring.service.UserService;
import cc.caker.study.spring.service.UserServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * AopUnitTest
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@Slf4j
public class AopUnitTest {

    @SneakyThrows
    @Test
    public void testMatch() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* cc.caker.study.spring.service.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        log.info("{}", pointcut.matches(clazz));
        log.info("{}", pointcut.matches(method, clazz));
    }

    @SneakyThrows
    @Test
    public void testAop() {
        // 目标对象
        UserService userService = new UserServiceImpl();

        // 组装代理信息
        AdvisedSupport advised = new AdvisedSupport();
        advised.setTargetSource(new TargetSource(userService));
        advised.setMethodInterceptor(new UserServiceInterceptor());
        advised.setMethodMatcher(new AspectJExpressionPointcut("execution(* cc.caker.study.spring.service.UserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        UserService jdkProxy = (UserService) new JdkDynamicAopProxy(advised).getProxy();
        // 测试调用
        log.info("测试结果：" + jdkProxy.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        UserService cglibProxy = (UserService) new Cglib2AopProxy(advised).getProxy();
        // 测试调用
        log.info("测试结果：" + cglibProxy.register("花花"));
    }
}
