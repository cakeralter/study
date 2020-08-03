package cc.caker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients("cc.caker")
@SpringBootApplication
public class ConsumerServApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServApplication.class, args);
    }
}
