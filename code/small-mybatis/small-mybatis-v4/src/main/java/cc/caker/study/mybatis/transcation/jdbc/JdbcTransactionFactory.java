package cc.caker.study.mybatis.transcation.jdbc;

import cc.caker.study.mybatis.session.TransactionIsolationLevel;
import cc.caker.study.mybatis.transcation.Transaction;
import cc.caker.study.mybatis.transcation.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * JdbcTranscationFactory
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
