package cc.caker.study.mybatis;

import cc.caker.study.mybatis.binding.MapperProxyFactory;
import cc.caker.study.mybatis.dao.UserDao;
import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * ApiTest
 *
 * @author cakeralter
 * @date 2022/5/22
 * @since 1.0
 */
@Slf4j
public class ApiTest {

    @Test
    public void testProxy() {
        MapperProxyFactory<UserDao> factory = new MapperProxyFactory<>(UserDao.class);
        Map<String, String> sqlSession = MapUtil.<String, String>builder()
                .put("cc.caker.study.mybatis.dao.UserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名")
                .put("cc.caker.study.mybatis.dao.UserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄")
                .build();

        UserDao userDao = factory.newInstance(sqlSession);
        String userName = userDao.queryUserName("10001");
        log.info("{}", userName);
    }
}
