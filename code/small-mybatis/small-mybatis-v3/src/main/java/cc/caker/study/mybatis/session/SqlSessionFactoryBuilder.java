package cc.caker.study.mybatis.session;

import cc.caker.study.mybatis.builder.xml.XMLConfigBuilder;
import cc.caker.study.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * SqlSessionFactoryBuilder
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
