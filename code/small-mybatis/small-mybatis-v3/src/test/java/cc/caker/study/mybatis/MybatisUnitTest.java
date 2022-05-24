package cc.caker.study.mybatis;

import cc.caker.study.mybatis.dao.UserDao;
import cc.caker.study.mybatis.io.Resources;
import cc.caker.study.mybatis.session.SqlSession;
import cc.caker.study.mybatis.session.SqlSessionFactory;
import cc.caker.study.mybatis.session.SqlSessionFactoryBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.Reader;

/**
 * MybatisUnitTest
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
@Slf4j
public class MybatisUnitTest {

    @SneakyThrows
    @Test
    public void test() {
        // 1. 获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 3. execute
        String res = userDao.queryUserInfoById("100001");
        log.info("res = {}", res);
    }
}
