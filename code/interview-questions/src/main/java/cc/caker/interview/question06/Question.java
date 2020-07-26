package cc.caker.interview.question06;

/**
 * 接口和抽象类中属性重名
 *
 * @author cakeralter
 * @date 2020/7/26
 */
public class Question {

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.print();
    }
}

interface BaseInterface {

    int x = 1;
    int y = 2;
}

abstract class BaseClass {

    int x = 11;
    int z = 33;
}

class Sub extends BaseClass implements BaseInterface {

    public void print() {
        // 报错 对 'x' 的引用不明确，'BaseClass.x' 和 'BaseInterface.x' 均匹配
//        System.out.println(x);
        // BaseInterface.x
        System.out.println(BaseInterface.x);
        // BaseClass.x
        System.out.println(super.x);
        System.out.println(y);
        System.out.println(z);
    }
}
