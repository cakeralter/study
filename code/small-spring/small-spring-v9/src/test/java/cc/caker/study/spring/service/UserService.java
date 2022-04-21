package cc.caker.study.spring.service;

import cc.caker.study.spring.dao.UserDao;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
@Slf4j
@Getter
@Setter
public class UserService {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return String.format("%s -%s -%s - %s", uId, userDao.queryUserName(uId), company, location);
    }
}
