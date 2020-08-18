package top.caker.stream;

import cn.hutool.core.collection.ListUtil;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
public class StreamTest {

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
}
