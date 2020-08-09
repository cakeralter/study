package cc.caker.springboot.component.log;

import cc.caker.springboot.repo.model.db2.Log;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
@Component
public class SysLogService {

    private final LinkedBlockingQueue<Log> queue = new LinkedBlockingQueue<>();

    public void add(Log log) {
        queue.add(log);
    }

    public Log poll() throws InterruptedException {
        return queue.poll(1, TimeUnit.SECONDS);
    }
}
