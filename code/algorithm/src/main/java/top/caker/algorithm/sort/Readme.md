> 作者: Damonarelv-5
>
> 出处: [十大经典排序算法总结（JavaScript描述）](https://juejin.im/post/57dcd394a22b9d00610c5ec8)

## 排序算法

### 1. 排序算法说明

#### 1.1 排序的定义

> 对一序列对象根据某个关键字进行排序；
>
> 输入：n个数：a1,a2,a3,...,an 输出：n个数的排列:a1',a2',a3',...,an'，使得a1'<=a2'<=a3'<=...<=an'。 

#### 1.2 评述算法优劣术语的说明

- **稳定:** 如果a原本在b前面，而a=b，排序之后a仍然在b的前面。

- **不稳定:** 如果a原本在b的前面，而a=b，排序之后a可能会出现在b的后面。

- **内排序:** 所有排序操作都在内存中完成。

- **外排序:** 排序通过磁盘和内存的数据传输进行。

- **时间复杂度:** 算法执行所耗费的时间。

- **空间复杂度:** 算法执行所耗费的内存。

#### 1.3 常见排序算法对比

![常见排序算法效率对比](https://gitee.com/cakeralter/images/raw/master/20200407111414.png)

**图片名词解释:** 

- n: 数据规模

- k:“桶”的个数

- In-place: 占用常数内存，不占用额外内存

- Out-place: 占用额外内存

#### 1.4 排序分类

![排序分类](https://gitee.com/cakeralter/images/raw/master/20200407111938.jpg)

### 2. 经典排序算法

#### 2.1 冒泡排序(Bubble Sort)

2.1.1 算法描述

> 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。

2.1.2 算法实现

- 实现描述

    **i.** 比较相邻的a,b元素，若 a > b，则交换a和b的位置；
    
    **ii.** 按顺序对所有相邻元素进行i操作，则第一轮冒泡结束后最大的一个元素应该在最右侧；
    
    **iii.** 重复执行i，ii的冒泡操作完成排序(已完成冒泡的元素在下一轮跳过)。

- 代码

```java
    public static int[] simple(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
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
```

- 演示

![冒泡排序](https://gitee.com/cakeralter/images/raw/master/20200407113958.gif)

- 算法改进

    i. 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。
    
    ```java
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
    ```
    
    ii. 传统冒泡排序中每一趟排序操作只能找到一个最大值或最小值,我们考虑利用在每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值(最大者和最小者), 从而使排序趟数几乎减少了一半。
    
    ```java
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
    ```

2.1.3 算法分析

- 最佳情况: 当输入的数据已经是正序时，T(n) = O(n)

- 最差情况: 当输入的数据是反序时，T(n) = O(n²)

- 平均情况: T(n) = O(n²)

#### 2.2 选择排序(Selection Sort)

2.2.1 算法描述

> 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。

2.2.2 算法实现

- 实现描述

	**i.** 初始状态：无序区为R[1..n]，有序区为空；
	
	**ii.** 第i趟排序(i=1,2,3...n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
	
	**iii.** n-1趟结束，数组有序化了。

- 代码

```java
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
```

- 演示

![选择排序](https://gitee.com/cakeralter/images/raw/master/20200408093659.gif)

2.2.3 算法分析

- 最佳情况: T(n) = O(n²)

- 最差情况: T(n) = O(n²)

- 平均情况: T(n) = O(n²)

#### 2.3 插入排序(Insertion Sort)

2.3.1 算法描述

> 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。

2.3.2 算法实现

- 实现描述

	**i.** 从第一个元素开始(该元素可以认为已经被排序)；
	
	**ii.** 取出下一个元素，在已经排序的元素序列中从后向前扫描；
	
	**iii.** 如果该元素（已排序）大于新元素，将该元素移到下一位置；
	
	**iv.** 重复步骤iii，直到找到已排序的元素小于或者等于新元素的位置；
	
	**v.** 将新元素插入到该位置后；
	
	**vi.** 重复步骤2~5。

- 代码

```java
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
```

- 演示

![插入排序](https://gitee.com/cakeralter/images/raw/master/20200408165628.gif)

- 算法改进

    i.查找插入位置时使用二分查找的方式。
    
    ```java
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
    ```

2.3.3 算法分析

- 最佳情况: T(n) = O(n)

- 最差情况: T(n) = O(n²)

- 平均情况: T(n) = O(n²)

#### 2.4 希尔排序(Shell Sort)

2.4.1 算法描述

> 希尔排序(Shell's Sort)是插入排序的一种又称“缩小增量排序”（Diminishing Increment Sort），是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。
>
> 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。

2.4.2 算法实现

- 实现描述

	**i.** 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
	
	**ii.** 按增量序列个数k，对序列进行k 趟排序；
	
	**iii.** 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。

- 代码

```java
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
```

- 演示

![希尔排序](https://gitee.com/cakeralter/images/raw/master/20200409202605.png)

2.4.3 算法分析

- 最佳情况: T(n) = O(n㏒n)

- 最差情况: T(n) = O(n㏒n)

- 平均情况: T(n) = O(n㏒n)

#### 2.5 归并排序(Merge Sort)

2.5.1 算法描述

> 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。

2.5.2 算法实现

- 实现描述

	**i.** 把长度为n的输入序列分成两个长度为n/2的子序列；
	
	**ii.** 对这两个子序列分别采用归并排序；
	
	**iii.** 将两个排序好的子序列合并成一个最终的排序序列。

- 代码

```java
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
    public static int[] sort(int[] left, int[] right) {
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
```

- 演示

![归并排序](https://gitee.com/cakeralter/images/raw/master/20200410193743.gif)

2.5.3 算法分析

- 最佳情况: T(n) = O(n)

- 最差情况: T(n) = O(n㏒n)

- 平均情况: T(n) = O(n㏒n)

#### 2.6 快速排序(Quick Sort)

2.6.1 算法描述

> 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。

2.6.2 算法实现

- 实现描述

	**i.** 从数列中挑出一个元素，称为 "基准"（pivot）；
	
	**ii.** 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
	
	**iii.** 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。

- 代码

```java
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
```

- 演示

![快速排序](https://gitee.com/cakeralter/images/raw/master/20200411140948.gif)

2.6.3 算法分析

- 最佳情况: T(n) = O(n㏒n)

- 最差情况: T(n) = O(n²)

- 平均情况: T(n) = O(n㏒n)

#### 2.7 堆排序(Heap Sort)

2.7.1 算法描述

> 

2.7.2 算法实现

- 实现描述

	

- 代码



- 演示



- 算法改进



2.7.3 算法分析

- 最佳情况: T(n) = 

- 最差情况: T(n) = 

- 平均情况: T(n) = 

#### 2.8 计数排序(Counting Sort)

2.8.1 算法描述

> 

2.8.2 算法实现

- 实现描述

	

- 代码



- 演示



- 算法改进



2.8.3 算法分析

- 最佳情况: T(n) = 

- 最差情况: T(n) = 

- 平均情况: T(n) = 

#### 2.9 桶排序(Bucket Sort)

2.9.1 算法描述

> 

2.9.2 算法实现

- 实现描述

	

- 代码



- 演示



- 算法改进



2.9.3 算法分析

- 最佳情况: T(n) = 

- 最差情况: T(n) = 

- 平均情况: T(n) = 

#### 2.10 基数排序(Radix Sort)

2.10.1 算法描述

> 

2.10.2 算法实现

- 实现描述

	

- 代码



- 演示



- 算法改进



2.10.3 算法分析

- 最佳情况: T(n) = 

- 最差情况: T(n) = 

- 平均情况: T(n) = 

### 3. 总结