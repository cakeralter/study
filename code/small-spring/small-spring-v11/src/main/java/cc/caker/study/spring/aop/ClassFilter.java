package cc.caker.study.spring.aop;

/**
 * ClassFilter
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
