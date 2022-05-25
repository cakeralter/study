package cc.caker.study.mybatis.binding;

import cc.caker.study.mybatis.mapping.MappedStatement;
import cc.caker.study.mybatis.mapping.SqlCommandType;
import cc.caker.study.mybatis.session.Configuration;
import cc.caker.study.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * MapperMethod
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    /**
     * execute
     *
     * @param sqlSession
     * @param args
     * @return
     */
    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result = null;
        switch (command.type) {
            case INSERT:
            case DELETE:
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.name, args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    /**
     * SQL 指令
     */
    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }
}
