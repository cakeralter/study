package cc.caker.study.mybatis.session.defaults;

import cc.caker.study.mybatis.binding.MapperRegistry;
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

    private MapperRegistry mapperRegistry;

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
