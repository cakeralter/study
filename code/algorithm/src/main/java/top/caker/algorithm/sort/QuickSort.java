package top.caker.algorithm.sort;

/**
 * 快速排序
 *
 * @author cakeralter
 * @date 2020/4/11
 */
public class QuickSort {

    /**
     * 快速排序
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] simple(int[] array) {
        sort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 快速排序
     *
     * @param array 数组
     * @param left  数组起始下标(包含)
     * @param right 数组结束下标(包含)
     */
    private static void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int point = left - 1, temp;
        for (int i = left; i <= right; i++) {
            if (array[i] <= array[right]) {
                point++;
                temp = array[point];
                array[point] = array[i];
                array[i] = temp;
            }
        }

        // 递归排序基准点两侧数组
        sort(array, left, point - 1);
        sort(array, point + 1, right);
    }
}
