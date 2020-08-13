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
        UM("User Management", 3),
        CM("Content Management", 4),
        PM("Product Management", 5),
        OM("Order Management", 6);

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
     * 商品状态
     */
    @Getter
    @RequiredArgsConstructor
    enum GoodsStatus {
        DOWN("下架", 0),
        UP("上架", 1);

        private final String name;
        private final int value;
    }

    /**
     * 订单状态
     */
    enum OrderStatus {
    }

    /**
     * 用户账号类型 - 大致分类
     */
    @Getter
    @RequiredArgsConstructor
    enum UserType {
        VISITOR("游客", 1),
        USER("普通用户", 2),
        ADMIN("管理员", 3);

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
        REGISTER("注册", "verify_code", "/mail/"),
        RESET_PASSWORD("重置密码", "", ""),
        LOGIN("登录", "", "");

        private final String name;
        private final String template;
        private final String path;
    }
}
