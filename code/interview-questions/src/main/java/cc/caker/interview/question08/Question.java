package cc.caker.interview.question08;

/**
 * @author cakeralter
 * @date 2020/8/1
 */
public class Question {

    public static void main(String[] args) {

        String s1 = "Java";
        String s2 = "Script";
        String s3 = "JavaScript";
        String s4 = "Java" + "Script";
        String s5 = s1 + "Script";
        String s6 = "Java" + s2;
        String s7 = s1 + s2;
        String s8 = (s1 + s2).intern();
        final String s9 = "Java";
        String s10 = s9 + s2;
        String s11 = s9 + "Script";

        /*
         * 1. 常量与常量拼接的结果在常量池中，且常量池不会存储多个相同的常量；
         * 2. 变量参与的拼接在堆中进行；
         * 3. intern()方法返回常量池中的地址。
         * */
        // true
        System.out.println(s3 == s4);
        // false
        System.out.println(s3 == s5);
        // false
        System.out.println(s3 == s6);
        // false
        System.out.println(s3 == s7);
        // true
        System.out.println(s3 == s8);
        // false
        System.out.println(s3 == s10);
        // true --- s9是常量
        System.out.println(s3 == s11);
    }
}
