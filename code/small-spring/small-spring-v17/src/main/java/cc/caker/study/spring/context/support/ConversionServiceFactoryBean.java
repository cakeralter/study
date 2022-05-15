package cc.caker.study.spring.context.support;

import cc.caker.study.spring.beans.factory.FactoryBean;
import cc.caker.study.spring.beans.factory.InitializingBean;
import cc.caker.study.spring.core.convert.ConversionService;
import cc.caker.study.spring.core.convert.converter.Converter;
import cc.caker.study.spring.core.convert.converter.ConverterFactory;
import cc.caker.study.spring.core.convert.converter.ConverterRegistry;
import cc.caker.study.spring.core.convert.converter.GenericConverter;
import cc.caker.study.spring.core.convert.support.DefaultConversionService;
import cc.caker.study.spring.core.convert.support.GenericConversionService;
import lombok.Setter;

import java.util.Set;

/**
 * ConversionServiceFactoryBean
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    @Setter
    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }
}
