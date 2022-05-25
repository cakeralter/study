package cc.caker.study.mybatis.session;

/**
 * SqlSession
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
public interface SqlSession {

    /**
     * 根据指定sqlId获取一条记录封装对象
     *
     * @param statement
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定sqlId获取一条记录封装对象
     *
     * @param statement
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 根据类型获取Mapper
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> type);

    /**
     * Retrieves current configuration
     * 得到配置
     *
     * @return Configuration
     */
    Configuration getConfiguration();
}
