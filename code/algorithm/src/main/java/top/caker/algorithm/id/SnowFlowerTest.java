package top.caker.algorithm.id;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 雪花算法
 * 64位 = 1位保留 + 41位时间戳 + 5位机房编号 + 5位机器编号 + 12位流水
 *
 * @author cakeralter
 * @date 2020/7/25
 */
public class SnowFlowerTest {

    public static void main(String[] args) {
        SnowFlower flower = new SnowFlower(25, 12);
        ExecutorService service = Executors.newFixedThreadPool(9);
        Set<Long> set = new HashSet<>();
        Set<Long> ids = Collections.synchronizedSet(set);

        for (int i = 0; i < 10000; i++) {
            service.submit(() -> {
                long id = flower.create();
                ids.add(id);
                System.out.printf("%s生成ID：%016x\n", Thread.currentThread().getName(), id);
            });
        }

        service.shutdown();
        while (!service.isTerminated()) ;
        System.out.println(ids.size());
    }
}

class SnowFlower {

    /**
     * 起始时间戳
     */
    private static final long START_TIMESTAMP = 1595769166470L;

    /**
     * 序列号流水位数
     */
    private static final long SEQUENCE_BIT = 12;
    /**
     * 序列号流水最大值
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    /**
     * 机器编号位数
     */
    private static final long MACHINE_BIT = 5;
    /**
     * 机器编号最大值
     */
    private static final long MAX_MACHINE = ~(-1L << MACHINE_BIT);
    /**
     * 机房编号位数
     */
    private static final long DATA_CENTER_BIT = 5;
    /**
     * 机房编号最大值
     */
    private static final long MAX_DATA_CENTER = ~(-1L << DATA_CENTER_BIT);

    /**
     * 每一位向左位移数
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 流水序列号
     */
    private long sequence = 0L;
    /**
     * 机器编号
     */
    private final long machineId;
    /**
     * 机房编号
     */
    private final long dataCenterId;
    /**
     * 最后一次生成的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * @param machineId    机器编号
     * @param dataCenterId 机房编号
     */
    public SnowFlower(long machineId, long dataCenterId) {
        if (machineId > MAX_MACHINE || machineId < 0) {
            throw new IllegalArgumentException("机器编号不合法");
        }
        if (dataCenterId > MAX_DATA_CENTER || dataCenterId < 0) {
            throw new IllegalArgumentException("机房编号不合法");
        }
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long create() {
        long millis = newMillis();
        if (millis < lastTimestamp) {
            // 防止时钟回拨
            throw new RuntimeException("时间戳不合法，请校准机器时钟");
        }

        if (millis == lastTimestamp) {
            // 相同时间序列自增 与运算防止超出最大值
            sequence = ++sequence & MAX_SEQUENCE;
            if (sequence == 0L) {
                // 同一时间序列号达到最大值则重新获取时间戳
                millis = newMillis();
            }
        } else {
            // 时间不同重置序列流水
            sequence = 0L;
        }

        lastTimestamp = millis;

        return (lastTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT
                | dataCenterId << DATA_CENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long newMillis() {
        long millis = System.currentTimeMillis();
        return millis <= lastTimestamp ? System.currentTimeMillis() : millis;
    }
}


