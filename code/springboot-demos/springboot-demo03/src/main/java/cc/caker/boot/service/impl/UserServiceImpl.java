package cc.caker.boot.service.impl;

import cc.caker.boot.constant.RedisEnum;
import cc.caker.boot.service.UserService;
import cc.caker.common.service.RedisService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author cakeralter
 * @date 2020/9/1
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final RedisService redisService;
    private final static long DEFAULT_EXPIRE = 3600 * 1000;

    @Override
    public String getHash(long uid, long sid) {
        // TODO 此处通过数据库验证uid,sid
        String key = String.format("%s::%d::%d", RedisEnum.SPIKE_HASH_KEY.getKey(), uid, sid);
        String hash = redisService.get(key);
        if (Strings.isNullOrEmpty(hash)) {
            hash = DigestUtils.md5DigestAsHex(String.format("%d%d%d", uid, System.currentTimeMillis(), sid).getBytes());
            redisService.put(key, hash, DEFAULT_EXPIRE);
        }
        return hash;
    }
}
