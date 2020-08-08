package cc.caker.springboot.util;

import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
public interface CodingUtils {

    /**
     * 公钥
     */
    String PUBLIC_KEY = "SpringBoot";

    /**
     * 加密
     *
     * @param str
     * @return
     */
    static String encrypt(String str, String salt) {
        if (StringUtils.isEmpty(str)) {
            salt = PUBLIC_KEY;
        }
        return DigestUtils.md5DigestAsHex(Base64Utils.encode((str + salt).getBytes()));
    }
}
