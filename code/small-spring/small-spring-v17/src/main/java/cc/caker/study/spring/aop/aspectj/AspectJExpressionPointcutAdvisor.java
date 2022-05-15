package cc.caker.study.spring.aop.aspectj;

import cc.caker.study.spring.aop.Pointcut;
import cc.caker.study.spring.aop.PointcutAdvisor;
import lombok.Setter;
import org.aopalliance.aop.Advice;

/**
 * AspectJExpressionPointcutAdvisor
 *
 * @author cakeralter
 * @date 2022/5/5
 * @since 1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 切面
     */
    private AspectJExpressionPointcut pointcut;
    /**
     * 切入点
     */
    @Setter
    private Advice advice;
    /**
     * 表达式
     */
    @Setter
    private String expression;


    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
