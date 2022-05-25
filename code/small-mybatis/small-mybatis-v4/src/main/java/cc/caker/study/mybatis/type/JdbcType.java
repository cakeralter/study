package cc.caker.study.mybatis.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * JdbcType
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    TIMESTAMP(Types.TIMESTAMP);

    private static Map<Integer, JdbcType> codeLookup = new HashMap<>();

    // 就将数字对应的枚举型放入 HashMap
    static {
        for (JdbcType type : JdbcType.values()) {
            codeLookup.put(type.TYPE_CODE, type);
        }
    }

    public final int TYPE_CODE;

    JdbcType(int code) {
        this.TYPE_CODE = code;
    }

    public static JdbcType forCode(int code) {
        return codeLookup.get(code);
    }
}
