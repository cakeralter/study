package cc.caker.study.spring.beans.factory.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * BeanDefinition
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
@RequiredArgsConstructor
@Getter
public class BeanDefinition {

    /**
     * Bean类型
     */
    private final Class beanClass;
}
