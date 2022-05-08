package cc.caker.study.spring.dao;

import cc.caker.study.spring.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao
 *
 * @author cakeralter
 * @date 2022/5/8
 * @since 1.0
 */
@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "汪敏，北京，亦庄");
        hashMap.put("10002", "刘亦菲，上海，尖沙咀");
        hashMap.put("10003", "王军，香港，铜锣湾");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
