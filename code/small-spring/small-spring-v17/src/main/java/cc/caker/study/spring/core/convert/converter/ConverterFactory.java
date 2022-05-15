package cc.caker.study.spring.core.convert.converter;

/**
 * ConverterFactory
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public interface ConverterFactory<S, R> {

    /**
     * 获取converter
     *
     * @param targetClass
     * @param <T>
     * @return
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetClass);
}
