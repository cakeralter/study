package cc.caker.study.spring.core.convert.support;

import cc.caker.study.spring.core.convert.converter.ConverterRegistry;

/**
 * DefaultConversionService
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型转换工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
