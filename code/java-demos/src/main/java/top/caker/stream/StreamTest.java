package top.caker.stream;

import cn.hutool.core.collection.ListUtil;
import org.junit.Test;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Stream的操作时惰性的，即只有终结流后才会实际进行中间操作
 *
 * @author cakeralter
 * @date 2020/8/18
 */
public class StreamTest {

    @Test
    public void testUnlimitedStream() {
        UnaryOperator<Integer> operator = x -> x + 2;
        Stream.iterate(0, operator).limit(10).forEach(System.out::println);

        Supplier<Double> supplier = Math::random;
        Stream.generate(supplier).limit(10).forEach(System.out::println);
    }

    /**
     * flatMap：平铺
     */
    @Test
    public void testFlatMap() {
        List<String> l1 = ListUtil.toList("wangmin", "luq", "xuj", "wulx", "xur");
        List<String> l2 = ListUtil.toList("liq", "shizh", "huxq");
        Stream<Stream<String>> map = l1.stream().map(x -> Stream.of(x + " wang"));
        map.forEach(System.out::print);
        System.out.println();
        Stream<String> flatMap = l1.stream().flatMap(x -> Stream.of(x + " wang"));
        flatMap.forEach(System.out::print);
        System.out.println();
    }

    @Test
    public void testReduce() {
        BinaryOperator<Double> operator = Double::sum;
        System.out.println(Stream.generate(Math::random).limit(10).reduce(55d, operator));

        // 计算1-100的值
        System.out.println(Stream.iterate(1, x -> ++x).limit(100).reduce(0, Integer::sum));
    }
}
