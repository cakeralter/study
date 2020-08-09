package cc.caker.springboot.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author cakeralter
 * @date 2020/8/4
 */
@MapperScan(basePackages = "cc.caker.springboot.repo.mapper.db2",
        sqlSessionFactoryRef = "db2SqlSessionFactory")
@EnableTransactionManagement
@Configuration
public class DB2Config {

    @ConfigurationProperties(prefix = "spring.datasource.db2")
    @Bean("db2DataSource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("db2TransactionManager")
    public TransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*Mapper.xml"));
        bean.setTypeAliasesPackage("cc.caker.springboot.repo.model.db2");
        bean.setPlugins(paginationInterceptor);
        return bean.getObject();
    }
}
