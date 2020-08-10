package cc.caker.boot.constant;

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

    /**
     * 资源类型
     */
    @Getter
    @RequiredArgsConstructor
    enum ResourceType {
        DIRECTORY("目录", 1),
        MENU("菜单", 2),
        INTERFACE("接口", 3),
        BUTTON("按钮", 4);

        private final String name;
        private final int value;
    }

    /**
     * 日志类型
     */
    @Getter
    @RequiredArgsConstructor
    enum LogType {
        NORMAL("正常", 1),
        EXCEPTION("异常", 2);

        private final String name;
        private final int value;
    }
}
