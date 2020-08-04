package cc.caker.springboot.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cakeralter
 * @since 2020/8/1
 */
@Configuration
public class MybatisPlusConfig {

    @Bean("db1PaginationInterceptor")
    public PaginationInterceptor paginationInterceptor1() {
        return new PaginationInterceptor();
    }

    @Bean("db2PaginationInterceptor")
    public PaginationInterceptor paginationInterceptor2() {
        return new PaginationInterceptor();
    }
}
