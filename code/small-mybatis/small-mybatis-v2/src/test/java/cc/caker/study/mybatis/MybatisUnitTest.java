package cc.caker.study.mybatis;

import cc.caker.study.mybatis.binding.MapperRegistry;
import cc.caker.study.mybatis.dao.UserDao;
import cc.caker.study.mybatis.session.SqlSession;
import cc.caker.study.mybatis.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * MybatisUnitTest
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
@Slf4j
public class MybatisUnitTest {

    @Test
    public void testMapperProxyFactory() {
        // 1. 注册Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("cc.caker.study.mybatis.dao");

        // 2. 获取SqlSession
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射接口
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 4. 调用
        String age = userDao.queryUserAge("100001");
        log.info("result = {}", age);
    }
}
