package cc.caker.study.spring.service;

import cc.caker.study.spring.beans.BeansException;
import cc.caker.study.spring.beans.factory.*;
import cc.caker.study.spring.context.ApplicationContext;
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
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public String queryUserInfo() {
        return String.format("%s-%s-%s-%s", uId, userDao.queryUserName(uId), company, location);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("BeanClassLoader = {}", classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        log.info("BeanName = {}", name);
    }
}
