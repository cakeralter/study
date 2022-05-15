package cc.caker.study.spring.core.convert.converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * GenericConverter
 *
 * @author cakeralter
 * @date 2022/5/15
 * @since 1.0
 */
public interface GenericConverter {

    /**
     * Return the source and target types that this converter can convert between.
     */
    Set<ConvertiblePair> getConvertibleTypes();

    /**
     * Convert the source object to the targetType described by the {@code TypeDescriptor}.
     *
     * @param source     the source object to convert (may be {@code null})
     * @param sourceType the type descriptor of the field we are converting from
     * @param targetType the type descriptor of the field we are converting to
     * @return the converted object
     */
    Object convert(Object source, Class sourceType, Class targetType);

    @Getter
    @RequiredArgsConstructor
    final class ConvertiblePair {

        private final Class<?> sourceType;
        private final Class<?> targetType;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);

        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }
}
