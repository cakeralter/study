package cc.caker.study.spring.aop;

/**
 * Pointcut
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
