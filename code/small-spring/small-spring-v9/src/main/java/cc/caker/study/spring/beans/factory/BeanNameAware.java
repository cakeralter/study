package cc.caker.study.spring.beans.factory;

/**
 * BeanNameAware
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
