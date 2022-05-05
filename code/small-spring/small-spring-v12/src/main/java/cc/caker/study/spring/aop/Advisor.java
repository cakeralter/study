package cc.caker.study.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
public interface Advisor {

    Advice getAdvice();
}
