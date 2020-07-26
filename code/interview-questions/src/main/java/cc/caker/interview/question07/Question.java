package cc.caker.interview.question07;

/**
 * @author cakeralter
 * @date 2020/7/26
 */
public class Question {

    public static void main(String[] args) {
        int i = -5;
        // 编译出错 - 单目运算符不能用于字面量，i++执行后 i = ++(-4)
//        i = ++(i++);
        System.out.println(i);
    }
}
