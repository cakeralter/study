package top.caker.demos.statictest;

/**
 * 子类可以访问父类静态属性和静态方法，但是不可以重写父类的静态方法而表现多态性
 *
 * @author cakeralter
 * @date 2020/7/26
 */
public class StaticTest {

    public static void main(String[] args) {
        System.out.println(Sub.a);
        System.out.println(Sub.b);
        Sub.print();
        Sub.show();

        Base base = new Sub();
        // 静态方法不表现多态 不会被重写
        base.print();
    }
}

class Base {
    static int a = 6;
    static int b = 7;

    static void print() {
        System.out.println("Base.print()");
    }

    static void show() {
        System.out.println("Base.show()");
    }
}

class Sub extends Base {

    static int a = 66;

    static void print() {
        System.out.println("Sub.print()");
    }
}
