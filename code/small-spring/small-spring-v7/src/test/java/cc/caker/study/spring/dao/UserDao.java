package cc.caker.study.spring.dao;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
@Slf4j
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod() {
        log.info("执行：init-method");
        hashMap.put("10001", "汪敏");
        hashMap.put("10002", "陈玉洁");
        hashMap.put("10003", "廖瑞雪");
    }

    public void destroyDataMethod() {
        log.info("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
