package cc.caker.study.spring.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * PropertyValue
 * 存储Bean的属性信息
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
@RequiredArgsConstructor
@Getter
public class PropertyValue {

    private final String name;
    private final Object value;
}
