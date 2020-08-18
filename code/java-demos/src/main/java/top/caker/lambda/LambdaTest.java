package top.caker.lambda;

import org.junit.Test;
import top.caker.clazz.Student;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author cakeralter
 * @date 2020/8/18
 */
public class LambdaTest {

    @Test
    public void testConsumer() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("知识");
    }

    @Test
    public void testSupplier() {
        Student student = new Student();
        student.setName("wangm");
        Supplier<String> supplier = student::getName;
        System.out.println(supplier.get());
    }

    @Test
    public void testFunction() {
        Student student = new Student();
        student.setName("wangm");
        Function<String, String> function = student::marry;
        System.out.println(function.apply("w"));
    }

    @Test
    public void testPredicate() {
        Student student = new Student();
        student.setName("wangm");
        Predicate<String> predicate = student::isTrue;
        System.out.println(predicate.test("wangm"));
    }
}
