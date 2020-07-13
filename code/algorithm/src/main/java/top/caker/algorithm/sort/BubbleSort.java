package top.caker.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author cakeralter
 * @date 2020/4/7
 */
public class BubbleSort {

    /**
     * 简单冒泡
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] simple(int[] array) {
        int temp, len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                // 循环比较相邻元素
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }

    /**
     * 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。
     * 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] improveOne(int[] array) {
        int temp, i = array.length - 1;
        while (i > 0) {
            int pos = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    pos = j;
                }
            }
            i = pos;
        }

        return array;
    }

    /**
     * 每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值(最大者和最小者), 从而使排序趟数几乎减少了一半。
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] improveTwo(int[] array) {
        int low = 0, high = array.length - 1;
        int temp;
        while (low < high) {
            // 正向冒泡 找最大
            for (int i = low; i < high; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            high--;

            // 反向冒泡 找最小
            for (int i = high; i > low; i--) {
                if (array[i - 1] > array[i]) {
                    temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                }
            }
            low++;
        }

        return array;
    }
}
