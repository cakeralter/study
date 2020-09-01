package cc.caker.boot.service;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
public interface UserService {

    /**
     * 获取秒杀哈希
     *
     * @param uid
     * @param sid
     * @return
     */
    String getHash(long uid, long sid);
}
