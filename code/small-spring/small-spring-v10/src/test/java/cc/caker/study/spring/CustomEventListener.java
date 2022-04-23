package cc.caker.study.spring;

import cc.caker.study.spring.context.ApplicationListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * CustomEventListener
 *
 * @author cakeralter
 * @date 2022/4/23
 * @since 1.0
 */
@Slf4j
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info("收到：" + event.getSource() + "消息;时间：" + new Date());
        log.info("消息：" + event.getId() + ":" + event.getMessage());
    }
}
