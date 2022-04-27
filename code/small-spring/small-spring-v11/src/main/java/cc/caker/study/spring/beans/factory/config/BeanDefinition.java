package cc.caker.study.spring.beans.factory.config;

import cc.caker.study.spring.beans.PropertyValues;
import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * BeanDefinition
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
@Setter
@Getter
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /**
     * Bean类型
     */
    private Class beanClass;
    /**
     * Bean属性信息
     */
    private PropertyValues propertyValues;
    /**
     * init method
     */
    private String initMethodName;
    /**
     * destroy method
     */
    private String destroyMethodName;
    /**
     * 作用域
     */
    private String scope = SCOPE_SINGLETON;
    /**
     * 单例
     */
    private boolean singleton = true;
    /**
     * 原型
     */
    private boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = ObjectUtil.defaultIfNull(propertyValues, new PropertyValues());
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
