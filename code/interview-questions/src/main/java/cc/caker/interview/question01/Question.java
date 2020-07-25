package cc.caker.interview.question01;

import java.io.PrintStream;

/**
 * @author cakeralter
 * @date 2020/7/19
 */
public class Question {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        method(a, b); // 编写method方法，要求在method调用后仅输入"a=100,b=200"

        System.out.println("a=" + a + ",b=" + b);
    }

    /**
     * 解法一
     *
     * @param a
     * @param b
     */
    /*public static void method(int a, int b) {
        System.out.println("a=" + 100 + ",b=" + 200);
        System.exit(-1);
    }*/

    /**
     * 解法二
     *
     * @param a
     * @param b
     */
    public static void method(int a, int b) {
        PrintStream printStream = new PrintStream(System.out) {
            @Override
            public void println(String x) {
                if ("a=10,b=20".equals(x)) {
                    x = "a=100,b=200";
                }
                super.println(x);
            }
        };
        System.setOut(printStream);
    }
}
