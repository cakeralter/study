package top.caker.demos.bit;

import org.junit.Test;

import java.util.SplittableRandom;

/**
 * @author cakeralter
 * @date 2020/5/14
 */
public class BitMapTest {

    @Test
    public void test() {
        final int length = 10000;
        final int test = 100;
        BitMap map = new BitMap(length);
        for (int i = 0; i < 10000; i++) {
            map.put(i);
        }

        SplittableRandom random = new SplittableRandom();
        int count = 0;
        while (count++ < test) {
            int num = random.split().nextInt(length);
            System.out.print("测试数据为 " + num + " , 结果为 " + map.exists(num));
            System.out.println();
            if (count % 5 == 0) {
                map.del(num);
                System.out.println("删除后结果为 " + map.exists(num));
            }
        }
        System.out.println(map);
    }
}
