package cc.caker.boot.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.websocket.server.ServerEndpointConfig;

/**
 * @author wangyl
 * @date 2019/6/21
 * @description 自定义socket configurator
 */
public class CustomSpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    /**
     * Spring application context.
     */
    private static volatile BeanFactory context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomSpringConfigurator.context = applicationContext;
    }

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
