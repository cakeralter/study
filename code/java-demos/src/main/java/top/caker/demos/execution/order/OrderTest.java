package top.caker.demos.execution.order;

/**
 * 代码块及构造函数等执行顺序：
 * <b>父类静态代码块 -> 子类静态代码块 -> 父类代码块 -> 父类构造函数 -> 子类代码块 -> 子类构造函数</b>
 * <p>
 * 赋值顺序：
 * <b>默认初始化 -> 显式赋值 / 代码块赋值 -> 构造器赋值 -> 方法赋值</b>
 *
 * @author cakeralter
 * @date 2020/7/25
 */
public class OrderTest {

    public static void main(String[] args) {
        System.out.print("OrderTest的main方法 -> ");
        new Sub();
        new Sub().print();
    }
}

class Base {

    static {
        // 1
        System.out.print("父类静态代码块 -> ");
    }

    {
        // 3
        System.out.print("父类代码块 -> ");
    }

    public Base() {
        // 4
        System.out.print("父类构造函数 -> ");
    }
}

class Sub extends Base {

    /**
     * 显式赋值
     */
    String a = "b赋初值";
    static String c = "c赋初值";

    /*
     * 代码块赋值
     */ {
        // 代码块和赋初值操作执行顺序看具体代码顺序
        a = "a代码块";
        b = "b代码块";
    }

    static {
        c = "c代码块";
        d = "d代码块";
    }

    String b = "a赋初值";
    static String d = "d赋初值";

    static {
        // 2
        System.out.print("子类静态代码块 -> ");
    }

    {
        // 5
        System.out.print("子类代码块 -> ");
    }

    public Sub() {
        // 6
        System.out.print("子类构造函数 -> ");
    }

    public void print() {
        System.out.println("子类print方法");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}
