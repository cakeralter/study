package cc.caker.boot.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
public interface Enumerations {

    /**
     * 模块分类
     */
    @Getter
    @RequiredArgsConstructor
    enum ModuleType {
        GL("Global", 1),
        SM("System Management", 2),
        UM("User Management", 3);

        private final String name;
        private final int value;
    }

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

    /**
     * 操作类型
     */
    @Getter
    @RequiredArgsConstructor
    enum OperationType {
        REGISTER("注册", 1),
        RESET_PASSWORD("重置密码", 2),
        LOGIN("登录", 3);

        private final String name;
        private final int value;
    }
}
