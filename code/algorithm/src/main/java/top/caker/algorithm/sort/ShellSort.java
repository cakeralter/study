package top.caker.algorithm.sort;

/**
 * 希尔排序
 *
 * @author cakeralter
 * @date 2020/4/9
 */
public class ShellSort {

    /**
     * 希尔排序
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] simple(int[] array) {
        int temp, gap = 0, len = array.length;
        final int step = 6;
        // 计算间隔序列
        while (gap <= len / step) {
            gap = gap * step + 1;
        }
        do {
            // 内层插入排序
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > temp) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = temp;
            }
        } while ((gap /= step) > 0);

        return array;
    }
}
