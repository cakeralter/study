package cc.caker.study.mybatis.dao;

/**
 * UserDao
 *
 * @author cakeralter
 * @date 2022/5/22
 * @since 1.0
 */
public interface UserDao {

    String queryUserName(String id);

    Integer queryUserAge(String id);
}
