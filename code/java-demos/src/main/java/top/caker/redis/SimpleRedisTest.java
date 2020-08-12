package top.caker.redis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;
import top.caker.serialize.bean.Student;
import top.caker.serialize.bean.Teacher;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author cakeralter
 * @date 2020/5/13
 */
public class SimpleRedisTest {

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

    @Test
    public void testString() {
        Teacher teacher = new Teacher("001", "汪敏", "女", 26);
        Student student = new Student("10001", "卢倩", "女", 24);
        teacher.addStudent(student);

        jedis.set("teacher::" + teacher.getId(), JSONUtil.toJsonStr(teacher));
        String result = jedis.get("teacher::" + teacher.getId());
        Teacher bean = JSONUtil.toBean(result, Teacher.class);
        System.out.println(bean);
    }

    @Test
    public void testHash() {
        jedis.hset("teacher", "no", "001");
        jedis.hset("teacher", "age", "24");
        jedis.hset("teacher", "name", "陈玉颖");

        Map<String, String> teacher = jedis.hgetAll("teacher");
        teacher.entrySet().forEach(System.out::println);
    }

    @Test
    public void testList() {
        jedis.lpush("orders", "order1", "order2", "order3", "order1");
        String order = jedis.rpop("orders");
        while (StrUtil.isNotBlank(order)) {
            System.out.println(order);
            order = jedis.rpop("orders");
        }
    }

    @Test
    public void testSet() {
        jedis.sadd("students", "张三", "李四", "王五", "小缘", "张三");
        Set<String> students = jedis.smembers("students");
        students.forEach(System.out::println);
    }

    @Test
    public void testSortedSet() {
        Map<String, Double> rank = new LinkedHashMap<>();
        rank.put("Clannad", 9.8);
        rank.put("为美好世界献上祝福", 8.1);
        rank.put("轻音少女", 9.1);
        rank.put("灼眼的夏娜", 8.0);
        jedis.zadd("rank", rank);

        Set<Tuple> tuples = jedis.zrevrangeWithScores("rank", 0, -1);
        tuples.forEach(System.out::println);
    }

    @After
    public void destroy() {
        pool.close();
    }
}
