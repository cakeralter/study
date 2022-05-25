package cc.caker.study.mybatis.mapping;

import cc.caker.study.mybatis.session.Configuration;
import cc.caker.study.mybatis.type.JdbcType;

/**
 * 参数映射 #{property,javaType=int,jdbcType=NUMERIC}
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public class ParameterMapping {

    private Configuration configuration;
    private String property;
    private Class<?> javaType = Object.class;
    private JdbcType jdbcType;

    private ParameterMapping() {
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public static class Builder {

        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }
    }
}
