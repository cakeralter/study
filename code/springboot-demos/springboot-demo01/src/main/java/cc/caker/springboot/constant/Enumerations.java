package cc.caker.springboot.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
public interface Enumerations {

    /**
     * 数据状态
     */
    @Getter
    @RequiredArgsConstructor
    enum Status {
        DISABLED("禁用", 0),
        ENABLED("启用", 1);

        private final String name;
        private final int value;
    }
}
