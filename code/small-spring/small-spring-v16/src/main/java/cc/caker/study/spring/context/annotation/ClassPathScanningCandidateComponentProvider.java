package cc.caker.study.spring.context.annotation;

import cc.caker.study.spring.beans.factory.config.BeanDefinition;
import cc.caker.study.spring.stereotype.Component;
import cn.hutool.core.util.ClassUtil;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ClassPathScanningCandidateComponentProvider
 *
 * @author cakeralter
 * @date 2022/5/8
 * @since 1.0
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackages) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackages, Component.class);
        for (Class<?> aClass : classes) {
            candidates.add(new BeanDefinition(aClass));
        }
        return candidates;
    }
}
