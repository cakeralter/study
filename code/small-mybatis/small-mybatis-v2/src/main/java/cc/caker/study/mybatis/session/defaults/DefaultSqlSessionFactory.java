package cc.caker.study.mybatis.session.defaults;

import cc.caker.study.mybatis.binding.MapperRegistry;
import cc.caker.study.mybatis.session.SqlSession;
import cc.caker.study.mybatis.session.SqlSessionFactory;
import lombok.AllArgsConstructor;

/**
 * DefaultSqlSessionFactory
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
@AllArgsConstructor
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
