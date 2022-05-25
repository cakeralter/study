package cc.caker.study.mybatis.session;

import java.sql.Connection;

/**
 * TransactionIsolationLevel
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public enum TransactionIsolationLevel {

    NONE(Connection.TRANSACTION_NONE),

    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),

    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),

    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),

    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
