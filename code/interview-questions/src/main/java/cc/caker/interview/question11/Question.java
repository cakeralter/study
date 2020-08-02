package cc.caker.interview.question11;

import java.util.Arrays;
import java.util.List;

/**
 * @author cakeralter
 * @date 2020/8/2
 */
public class Question {

    public static void main(String[] args) {
        List list1 = Arrays.asList(new String[]{"AA", "BB"});
        List list2 = Arrays.asList(new int[]{1, 2, 3});
        List list3 = Arrays.asList(new Integer[]{1, 2, 3});

        // 2
        System.out.println(list1.size());
        // 1 --- 基本类型数组会被认为是一个元素
        System.out.println(list2.size());
        // 3
        System.out.println(list3.size());
    }
}
