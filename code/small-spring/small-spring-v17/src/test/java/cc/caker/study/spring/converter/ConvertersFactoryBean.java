package cc.caker.study.spring.converter;

import cc.caker.study.spring.beans.factory.FactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * ConvertersFactoryBean
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public class ConvertersFactoryBean implements FactoryBean<Set<?>> {

    @Override
    public Set<?> getObject() throws Exception {
        HashSet<Object> converters = new HashSet<>();
        StringToLocalDateConverter stringToLocalDateConverter = new StringToLocalDateConverter("yyyy-MM-dd");
        converters.add(stringToLocalDateConverter);
        return converters;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
