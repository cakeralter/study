package cc.caker.interview.question05;

/**
 * @author cakeralter
 * @date 2020/7/25
 */
public class Question {

    public static void main(String[] args) {
        Object o1 = true ? new Integer(1) : new Double(2.0);
        // 三元运算符两端类型一致，所以会进行自动类型提升 --- 1.0
        System.out.println(o1);

        Object o2 = null;
        if (true) {
            o2 = new Integer(1);
        } else {
            o2 = new Double(2.0);
        }
        // 1
        System.out.println(o2);
    }
}
