package cc.caker.study.spring;

import cc.caker.study.spring.beans.factory.FactoryBean;
import cc.caker.study.spring.dao.UserDao;
import cc.caker.study.spring.uitl.ClassUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * ProxyFactoryBean
 *
 * @author cakeralter
 * @date 2022/4/21
 * @since 1.0
 */
public class ProxyFactoryBean implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return (UserDao) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[]{UserDao.class}, (o, m, params) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");

            return m.getName() + "被代理执行..." + hashMap.get(params[0].toString());
        });
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
