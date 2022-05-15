package cc.caker.study.spring.core.convert;

/**
 * ConvertionService
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public interface ConversionService {

    /**
     * Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}.
     */
    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     */
    <T> T convert(Object source, Class<T> targetType);
}
