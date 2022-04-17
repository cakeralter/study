package cc.caker.study.spring.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * UserDao
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "陈颖");
        hashMap.put("10002", "卢倩");
        hashMap.put("10003", "王洁");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
