package cc.caker.study.spring;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * UserServiceInterceptor
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@Slf4j
public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            log.info("监控 - Begin By AOP");
            log.info("方法名称：" + invocation.getMethod());
            log.info("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            log.info("监控 - End");
        }
    }
}
