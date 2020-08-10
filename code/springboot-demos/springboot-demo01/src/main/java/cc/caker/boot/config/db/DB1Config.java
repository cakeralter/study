package cc.caker.boot.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "cc.caker.boot.repo.mapper.db1",
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
    public TransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Primary
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(PaginationInterceptor paginationInterceptor) throws Exception {
        // 此处有坑 不能用 SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*Mapper.xml"));
        bean.setTypeAliasesPackage("cc.caker.boot.repo.model.db1");
        // 多数据源下需要手动注入分页插件
        bean.setPlugins(paginationInterceptor);
//        MybatisConfiguration configuration = new MybatisConfiguration();
        // 配置打印完整SQL
//        configuration.setLogImpl(StdOutImpl.class);
//        bean.setConfiguration(configuration);
        return bean.getObject();
    }
}
