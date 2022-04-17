package cc.caker.study.spring.service;

import cc.caker.study.spring.dao.UserDao;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
@Slf4j
@Setter
@Getter
public class UserService {

    private String uId;
    private UserDao userDao;

    public void getUserInfo() {
        log.info("查询用户信息：" + userDao.queryUserName(uId));
    }
}
