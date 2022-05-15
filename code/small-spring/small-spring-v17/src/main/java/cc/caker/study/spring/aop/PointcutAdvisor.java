package cc.caker.study.spring.aop;

/**
 * PointcutAdvisor
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
