package top.caker.util;

import java.util.Random;

/**
 * 数组工具类
 *
 * @author cakeralter
 * @date 2020/4/7
 */
public final class ArrayUtils {

    private final static int MAX_VALUE = 1024 * 256;

    /**
     * 生成一个int[]随机数组
     *
     * @return array
     */
    public static int[] rand() {
        Random random = new Random();
        int[] array = new int[random.nextInt(MAX_VALUE)];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(MAX_VALUE);
        }

        return array;
    }

    /**
     * 生成一个int[]随机数组
     *
     * @return array
     */
    public static int[] rand(int len) {
        Random random = new Random();
        int[] array = new int[len];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(len);
        }

        return array;
    }
}
