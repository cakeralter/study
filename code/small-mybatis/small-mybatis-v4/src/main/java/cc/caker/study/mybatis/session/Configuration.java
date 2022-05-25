package cc.caker.study.mybatis.session;

import cc.caker.study.mybatis.binding.MapperRegistry;
import cc.caker.study.mybatis.datasource.druid.DruidDataSourceFactory;
import cc.caker.study.mybatis.mapping.Environment;
import cc.caker.study.mybatis.mapping.MappedStatement;
import cc.caker.study.mybatis.transcation.jdbc.JdbcTransactionFactory;
import cc.caker.study.mybatis.type.TypeAliasRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public class Configuration {

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);
    protected Environment environment;

    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
