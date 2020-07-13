package top.caker.algorithm.sort;

/**
 * 插入排序
 *
 * @author cakeralter
 * @date 2020/4/8
 */
public class InsertionSort {

    /**
     * 简单插入
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] simple(int[] array) {
        int temp, len = array.length;
        for (int i = 0; i < len - 1; i++) {
            temp = array[i + 1];
            int j = i;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j--];
            }
            array[j + 1] = temp;
        }

        return array;
    }

    /**
     * 查找插入位置时使用二分查找的方式
     *
     * @param array 数据
     * @return 顺序排序后的数组
     */
    public static int[] improveOne(int[] array) {
        int temp, left, right, mid, len = array.length;
        for (int i = 0; i < len - 1; i++) {
            left = 0;
            right = i;
            temp = array[i + 1];
            while (left <= right) {
                mid = (left + right) >> 1;
                if (array[mid] < temp) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            for (int j = i; j >= left; j--) {
                array[j + 1] = array[j];
            }
            array[left] = temp;
        }

        return array;
    }
}
