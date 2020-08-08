package cc.caker.springboot.config;

import cc.caker.springboot.component.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new ShiroRealm();
    }

    /*@Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        *//*
     * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
     * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
     * 加入这项配置能解决这个bug
     *//*
        creator.setUsePrefix(true);
        return creator;
    }*/

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
//        chain.addPathDefinition("/um/admin/**", "authc, roles[admin]");
//        chain.addPathDefinition("/um/admin/all", "anon");
//        chain.addPathDefinition("/um/logout", "anon");
//        chain.addPathDefinition("/um/login", "anon");
//        chain.addPathDefinition("/swagger-ui/**", "anon");
//        chain.addPathDefinition("/**", "authc");
        return chain;
    }
}
