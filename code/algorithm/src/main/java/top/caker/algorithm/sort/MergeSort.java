package top.caker.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author cakeralter
 * @date 2020/4/10
 */
public class MergeSort {

    /**
     * 分治
     * 递归将数组两两等分
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] divide(int[] array) {
        int len = array.length;
        final int two = 2;
        if (len < two) {
            return array;
        }
        int mid = len >> 1;

        return sort(divide(Arrays.copyOf(array, mid)),
                divide(Arrays.copyOfRange(array, mid, len)));
    }

    /**
     * 归并排序
     * 对每一级数组排序合并
     *
     * @param left  等分后左侧数组
     * @param right 等分后右侧数组
     * @return 顺序排序后的数组
     */
    private static int[] sort(int[] left, int[] right) {
        int lLen = left.length, rLen = right.length;
        int[] result = new int[lLen + rLen];
        int i = 0, j = 0;
        while (i < lLen && j < rLen) {
            if (left[i] < right[j]) {
                result[i + j] = left[i++];
            } else {
                result[i + j] = right[j++];
            }
        }
        // 剩余数据直接插入
        while (i < lLen) {
            result[i + j] = left[i++];
        }
        while (j < rLen) {
            result[i + j] = right[j++];
        }

        return result;
    }
}
