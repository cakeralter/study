package cc.caker.study.mybatis.session.defaults;

import cc.caker.study.mybatis.mapping.MappedStatement;
import cc.caker.study.mybatis.session.Configuration;
import cc.caker.study.mybatis.session.SqlSession;
import lombok.AllArgsConstructor;

/**
 * DefaultSqlSession
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
