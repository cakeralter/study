package cc.caker.boot.config;

import cc.caker.boot.component.CustomSpringConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author wangyl
 * @date 2019/6/21
 * @description
 */
@EnableWebSocket
@Configuration
public class SocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();

        return taskScheduler;
    }

    @Bean
    public CustomSpringConfigurator customSpringConfigurator() {

        return new CustomSpringConfigurator();
    }
}
