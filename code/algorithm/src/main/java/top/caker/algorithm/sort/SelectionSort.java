package top.caker.algorithm.sort;

/**
 * 选择排序
 *
 * @author cakeralter
 * @date 2020/4/8
 */
public class SelectionSort {

    /**
     * 简单选择
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] simple(int[] array) {
        /*int temp, len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }*/
        int temp, minIndex, len = array.length;
        for (int i = 0; i < len - 1; i++) {
            // 避免每次进行交换
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        return array;
    }
}
