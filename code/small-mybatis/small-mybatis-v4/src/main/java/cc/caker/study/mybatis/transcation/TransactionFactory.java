package cc.caker.study.mybatis.transcation;

import cc.caker.study.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * TranscationFactory
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public interface TransactionFactory {

    /**
     * 创建事务
     *
     * @param conn
     * @return
     */
    Transaction newTransaction(Connection conn);

    /**
     * 创建事务
     *
     * @param dataSource
     * @param level
     * @param autoCommit
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);
}
