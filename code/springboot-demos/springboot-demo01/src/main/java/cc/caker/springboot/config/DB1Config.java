package cc.caker.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@MapperScan(basePackages = "cc.caker.springboot.repo.mapper.db1",
        sqlSessionFactoryRef = "db1SqlSessionFactory")
@EnableTransactionManagement
@Configuration
public class DB1Config {

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Bean("db1DataSource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("db1TransactionManager")
    public TransactionManager transactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource,
                                               @Qualifier("db1PaginationInterceptor") PaginationInterceptor interceptor) throws Exception {
        // 此处有坑 不能用 SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*Mapper.xml"));
        bean.setPlugins(interceptor);
        return bean.getObject();
    }
}
