package cc.caker.study.spring.beans;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * BeanFactory
 *
 * @author cakeralter
 * @date 2022/4/6
 * @since 1.0
 */
public class BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = Maps.newHashMap();

    /**
     * 注册Bean
     *
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 获取Bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }
}
