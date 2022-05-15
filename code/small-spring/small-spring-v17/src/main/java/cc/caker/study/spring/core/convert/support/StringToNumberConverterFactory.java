package cc.caker.study.spring.core.convert.support;

import cc.caker.study.spring.core.convert.converter.Converter;
import cc.caker.study.spring.core.convert.converter.ConverterFactory;
import cc.caker.study.spring.uitl.NumberUtils;

/**
 * StringToNumberConverterFactory
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetClass) {
        return new StringToNumber<>(targetClass);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
