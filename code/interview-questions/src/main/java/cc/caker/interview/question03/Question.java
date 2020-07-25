package cc.caker.interview.question03;

/**
 * @author cakeralter
 * @date 2020/7/25
 */
public class Question {

    public static void main(String[] args) {
        Sub sub = new Sub();
        // 20
        System.out.println(sub.count);
        // 20
        sub.print();

        Base base = sub;
        // true
        System.out.println(base == sub);
        // 10
        System.out.println(base.count);
        // 20
        base.print();
    }
}

class Base {
    int count = 10;

    public void print() {
        System.out.println(this.count);
    }
}

class Sub extends Base {
    int count = 20;

    @Override
    public void print() {
        System.out.println(this.count);
    }
}

