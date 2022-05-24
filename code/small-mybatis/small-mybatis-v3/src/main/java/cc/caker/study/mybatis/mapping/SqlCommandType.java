package cc.caker.study.mybatis.mapping;

/**
 * SqlCommandType
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;
}
