package cc.caker.boot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author cakeralter
 * @since 2020/8/5
 */
@EnableJpaRepositories(basePackages = "cc.caker.boot.repo.dao.db2",
        transactionManagerRef = "db2TransactionManager", entityManagerFactoryRef = "db2EntityManagerFactory")
@EnableTransactionManagement
@Configuration
public class DB2Config {

    @ConfigurationProperties(prefix = "spring.datasource.db2")
    @Bean("db2DataSource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("cc.caker.boot.repo.entity.db2");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean("db2TransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
