package cc.caker.study.mybatis.transcation;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transcation
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}
