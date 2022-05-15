package cc.caker.study.spring.core.convert.converter;

/**
 * ConverterRegistry
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public interface ConverterRegistry {

    /**
     * Add a plain converter to this registry.
     *
     * @param converter
     */
    void addConverter(Converter<?, ?> converter);

    /**
     * Add a generic converter to this registry.
     *
     * @param converter
     */
    void addConverter(GenericConverter converter);

    /**
     * Add a ranged converter factory to this registry.
     * The convertible source/target type pair is derived from the ConverterFactory's parameterized types.
     *
     * @throws IllegalArgumentException if the parameterized types could not be resolved
     */
    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
