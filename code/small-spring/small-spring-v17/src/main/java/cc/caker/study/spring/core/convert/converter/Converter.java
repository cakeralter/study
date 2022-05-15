package cc.caker.study.spring.core.convert.converter;

/**
 * Converter
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public interface Converter<S, T> {

    /**
     * convert S to T
     *
     * @param source
     * @return
     */
    T convert(S source);
}
