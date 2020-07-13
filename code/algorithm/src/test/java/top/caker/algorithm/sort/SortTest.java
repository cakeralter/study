package top.caker.algorithm.sort;

import org.junit.Test;
import top.caker.util.ArrayUtils;

import java.util.Arrays;

/**
 * 冒泡排序测试
 *
 * @author cakeralter
 * @date 2020/4/7
 */
public final class SortTest {

    /**
     * 测试冒泡排序
     */
    @Test
    public void testBubbleSort() {
        int[] array1 = ArrayUtils.rand();
        int[] array2 = Arrays.copyOf(array1, array1.length);
        int[] array3 = Arrays.copyOf(array1, array1.length);
        System.out.println(array1.length);

        System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        int[] sort1 = BubbleSort.simple(array1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort1));
        System.out.println("耗时：" + (end - start) + "ms");

        System.out.println("********************************");
        System.out.println(Arrays.toString(array2));
        start = System.currentTimeMillis();
        int[] sort2 = BubbleSort.improveOne(array2);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort2));
        System.out.println("耗时：" + (end - start) + "ms");

        System.out.println("********************************");
        System.out.println(Arrays.toString(array3));
        start = System.currentTimeMillis();
        int[] sort3 = BubbleSort.improveTwo(array3);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort3));
        System.out.println("耗时：" + (end - start) + "ms");
    }

    /**
     * 测试选择排序
     */
    @Test
    public void testSelectionSort() {
        int[] array1 = ArrayUtils.rand();
        System.out.println(array1.length);

        System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        int[] sort1 = SelectionSort.simple(array1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort1));
        System.out.println("耗时：" + (end - start) + "ms");
    }

    /**
     * 测试插入排序
     */
    @Test
    public void testInsertionSort() {
        int[] array1 = ArrayUtils.rand();
        int[] array2 = Arrays.copyOf(array1, array1.length);
        System.out.println(array1.length);

        System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        int[] sort1 = InsertionSort.simple(array1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort1));
        System.out.println("耗时：" + (end - start) + "ms");

        System.out.println("********************************");
        System.out.println(Arrays.toString(array2));
        start = System.currentTimeMillis();
        int[] sort2 = InsertionSort.improveOne(array2);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort2));
        System.out.println("耗时：" + (end - start) + "ms");
    }

    /**
     * 测试希尔排序
     */
    @Test
    public void testShellSort() {
        int[] array1 = ArrayUtils.rand(512 * 1024);
        System.out.println(array1.length);

        System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        int[] sort1 = ShellSort.simple(array1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort1));
        System.out.println("耗时：" + (end - start) + "ms");
    }

    /**
     * 测试归并排序
     */
    @Test
    public void testMergeSort() {
        int[] array1 = ArrayUtils.rand(512 * 1024);
        System.out.println(array1.length);

        System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        int[] sort1 = MergeSort.divide(array1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort1));
        System.out.println("耗时：" + (end - start) + "ms");
    }

    /**
     * 测试快速排序
     */
    @Test
    public void testQuickSort() {
        int[] array1 = ArrayUtils.rand(1024);
        System.out.println(array1.length);

        System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        int[] sort1 = QuickSort.simple(array1);
        long end = System.currentTimeMillis();
        System.out.println(Arrays.toString(sort1));
        System.out.println("耗时：" + (end - start) + "ms");
    }
}
