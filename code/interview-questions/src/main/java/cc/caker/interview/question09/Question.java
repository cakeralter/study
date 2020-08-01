package cc.caker.interview.question09;

/**
 * @author cakeralter
 * @date 2020/8/1
 */
public class Question {

    public static void main(String[] args) {
        Test t = new Test();
        t.change(t.a, t.b);

        // wangmin
        System.out.println(t.a);
        // chengyuying
        System.out.println(t.b);
    }

    static class Test {

        String a = "wangmin";
        char[] b = {'a', 'h', 'e', 'n', 'g', 'y', 'u', 'y', 'i', 'n', 'g'};

        void change(String str, char[] ch) {
            str = "luqian";
            ch[0] = 'c';
        }
    }
}
