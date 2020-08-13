package cc.caker.boot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

/**
 * @author cakeralter
 * @date 2020/8/12
 */
public interface BaseUtils {

    /**
     * 生成随机字符串
     * <p>
     * 0-9 A-Z a-z => 48-57 65-90 97-122
     *
     * @param length 字符串长度
     * @return
     */
    static String randomCode(int length) {
        List<String> chs = new ArrayList<>(length);
        SplittableRandom random = new SplittableRandom();
        while (chs.size() < length) {
            char c = (char) (int) Math.ceil(random.split().nextInt(48) + (122 - 48));
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                chs.add(c + "");
            }
        }
        return String.join("", chs);
    }
}
