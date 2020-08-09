package cc.caker.springboot.util;

import com.google.common.base.Strings;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密
 *
 * @author cakeralter
 * @date 2020/8/8
 */
public interface EncryptUtils {

    /**
     * 公钥
     */
    String PUBLIC_KEY = "SpringBoot";
    /**
     * 加密的次数
     */
    int HASH_ITERATIONS = 1024;
    /**
     * 加密方式
     */
    String ENCRYPT_TYPE = "md5";

    /**
     * encrypt
     *
     * @param str
     * @param salt
     * @return
     */
    static String encrypt(String str, String salt) {
        if (Strings.isNullOrEmpty(salt)) {
            salt = PUBLIC_KEY;
        }
        return new SimpleHash(ENCRYPT_TYPE, str, salt, HASH_ITERATIONS).toHex();
    }
}
