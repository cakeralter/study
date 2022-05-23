package cc.caker.study.mybatis.dao;

/**
 * UserDao
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
public interface UserDao {

    String queryUserName(String uId);

    String queryUserAge(String uId);
}
