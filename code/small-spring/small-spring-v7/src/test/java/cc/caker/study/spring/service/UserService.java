package cc.caker.study.spring.service;

import cc.caker.study.spring.beans.factory.DisposableBean;
import cc.caker.study.spring.beans.factory.InitializingBean;
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
public class UserService implements InitializingBean, DisposableBean {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return String.format("%s-%s-%s-%s", uId, userDao.queryUserName(uId), company, location);
    }

    @Override
    public void destroy() throws Exception {
        log.info("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("执行：UserService.afterPropertiesSet");
    }
}
