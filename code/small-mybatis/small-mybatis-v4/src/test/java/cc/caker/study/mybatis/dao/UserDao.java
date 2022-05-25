package cc.caker.study.mybatis.dao;

import cc.caker.study.mybatis.po.User;

/**
 * UserDao
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public interface UserDao {

    User queryUserInfoById(Long uId);
}
