package cc.caker.study.spring.aop;

import cc.caker.study.spring.uitl.ClassUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * TargetSource
 * 代理目标对象
 *
 * @author cakeralter
 * @date 2022/4/27
 * @since 1.0
 */
@Getter
@RequiredArgsConstructor
public class TargetSource {

    private final Object target;

    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }
}
