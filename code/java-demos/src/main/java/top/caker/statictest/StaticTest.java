package top.caker.statictest;

/**
 * 子类可以访问父类静态属性和静态方法，但是不可以重写父类的静态方法而表现多态性
 * <p>
 * 接口中的默认方法可以被重写，重写方法必须为public权限；实现类不能访问接口的静态方法
 * <p>
 * 类优先 - 父类方法和接口默认方法重名时优先调用父类方法
 * <p>
 * 若类实现了多个接口且这些接口包含重名的默认方法（该类的父类没有提供可继承的同名方法），则必须重写这些方法
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


        SubInterface subInterface = new SubInterface();
        // 只能在包含接口类时调用静态方法
//        subInterface.paint();
        subInterface.draw();
        // 类优先 - 父类方法和接口默认方法重名时优先调用父类方法
        subInterface.write();
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

    public void write() {
        System.out.println("Base.write()");
    }
}

class Sub extends Base {

    static int a = 66;

    static void print() {
        System.out.println("Sub.print()");
    }
}


interface BaseInterface {

    static void paint() {
        System.out.println("BaseInterface.paint()");
    }

    /**
     * 默认方法的访问权限是public
     * 实现类重写时也必须为public
     */
    default void draw() {
        System.out.println("BaseInterface.draw()");
    }

    default void write() {
        System.out.println("BaseInterface.write()");
    }
}

class SubInterface extends Base implements BaseInterface {

    /*static void paint() {
        System.out.println("SubInterface.paint()");
    }*/

    @Override
    public void draw() {
        // 实现类中调用接口的默认方法
        BaseInterface.super.draw();
        System.out.println("SubInterface.draw()");
    }
}