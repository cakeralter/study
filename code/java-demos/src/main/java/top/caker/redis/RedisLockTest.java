package top.caker.redis;

import org.junit.Before;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * @author cakeralter
 * @date 2020/5/13
 */
public class RedisLockTest {

    private final static String HOST = "116.85.4.107";
    private final static String PASSWORD = "redis1996";
    private JedisPool pool;
    private Jedis jedis;

    @Before
    public void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, HOST, Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT, PASSWORD);
        jedis = pool.getResource();
    }


}
