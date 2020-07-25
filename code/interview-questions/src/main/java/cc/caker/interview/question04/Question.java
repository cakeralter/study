package cc.caker.interview.question04;

/**
 * @author cakeralter
 * @date 2020/7/25
 */
public class Question {

    public static void main(String[] args) {
        Base base = new Sub();
        // 可变参数和数组构成重写 --- Sub.print(int a, int[] arr)
        base.print(1, 2, 3);

        Sub sub = (Sub) base;
        // 重载确定参数优先 --- Sub.print(int a, int b, int c)
        sub.print(1, 2, 3);
    }
}

class Base {

    public void print(int a, int... arr) {
        System.out.println("Base.print(int a, int... arr)");
    }
}

class Sub extends Base {

    public void print(int a, int[] arr) {
        System.out.println("Sub.print(int a, int[] arr)");
    }

    public void print(int a, int b, int c) {
        System.out.println("Sub.print(int a, int b, int c)");
    }
}
