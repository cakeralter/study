package cc.caker.springboot.component.log;

import cc.caker.springboot.module.log.service.LogService;
import cc.caker.springboot.repo.model.db2.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author cakeralter
 * @date 2020/8/9
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SysLogTask implements Runnable {

    /**
     * 每次处理数量
     */
    private final static int DEFAULT_BATCH_SIZE = 64;
    private final SysLogService queue;
    private final LogService logService;
    private boolean active = true;
    private Thread thread;

    @PostConstruct
    public void init() {
        thread = new Thread(this);
        thread.start();
    }

    @PreDestroy
    public void close() {
        active = false;
    }

    @Override
    public void run() {
        List<Log> logs = new ArrayList<>(DEFAULT_BATCH_SIZE);
        while (active) {
            logs.clear();
            int size = 0;
            while (size < DEFAULT_BATCH_SIZE) {
                Log lg = null;
                try {
                    lg = queue.poll();
                } catch (InterruptedException e) {
                    log.info("出队出错: {}", e.getMessage());
                }
                if (Objects.isNull(lg)) {
                    break;
                }
                logs.add(lg);
                size++;
            }
            if (!logs.isEmpty()) {
                try {
                    //休眠10秒来模拟业务复杂，正在计算，测试之后大家别忘记删除这句话
                    Thread.sleep(10000);
                    logService.saveBatch(logs);
                } catch (Exception e) {
                    log.error("日志处理出错: {}", e.getMessage());
                }
            }
        }
    }
}
