package cc.caker.study.spring.beans.factory.config;

import cc.caker.study.spring.beans.PropertyValues;
import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

/**
 * BeanDefinition
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
@Getter
public class BeanDefinition {

    /**
     * Bean类型
     */
    private Class beanClass;
    /**
     * Bean属性信息
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = ObjectUtil.defaultIfNull(propertyValues, new PropertyValues());
    }
}
