package cc.caker.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author cakeralter
 */
@EnableTransactionManagement
@MapperScan
@SpringBootApplication
public class Boot03Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot03Application.class, args);
    }
}
