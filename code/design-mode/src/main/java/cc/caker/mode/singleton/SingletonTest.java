package cc.caker.mode.singleton;

/**
 * 单例模式
 * <p>
 * 饿汉式：初始加载耗时，线程安全
 * <p>
 * 懒汉式：可以延迟加载，需要考虑线程安全问题
 *
 * @author cakeralter
 * @date 2020/7/25
 */
public class SingletonTest {

    public static void main(String[] args) {
        // 饿汉式
        SingletonOne one1 = SingletonOne.getInstance();
        SingletonOne one2 = SingletonOne.getInstance();
        System.out.println("饿汉式：" + (one1 == one2));

        // 懒汉式单例一
        SingletonTwo two1 = SingletonTwo.getInstance();
        SingletonTwo two2 = SingletonTwo.getInstance();
        System.out.println("懒汉式一：" + (two1 == two2));

        // 懒汉式单例二
        SingletonThree three1 = SingletonThree.getInstance();
        SingletonThree three2 = SingletonThree.getInstance();
        System.out.println("懒汉式二：" + (three1 == three2));

        // 懒汉式单例三
        SingletonThree four1 = SingletonThree.getInstance();
        SingletonThree four2 = SingletonThree.getInstance();
        System.out.println("懒汉式三：" + (four1 == four2));
    }
}

/**
 * 饿汉式单例
 */
class SingletonOne {

    /**
     * <code>public static SingletonOne instance = new SingletonOne();</code>
     * 这种方式可能会出现 <b>SingletonOne.instance = null</b> 问题
     * 需要加final使instance成为常量
     */

    private final static SingletonOne INSTANCE = new SingletonOne();

    private SingletonOne() {
    }

    public static SingletonOne getInstance() {
        return INSTANCE;
    }
}

/**
 * 懒汉式单例 - 线程不安全
 */
class SingletonTwo {

    private static SingletonTwo instance;

    private SingletonTwo() {
    }

    public static SingletonTwo getInstance() {
        if (instance != null) {
            instance = new SingletonTwo();
        }

        return instance;
    }
}

/**
 * 懒汉式单例 - 同步方法实现（性能较低）
 */
class SingletonThree {

    private static SingletonThree instance;

    private SingletonThree() {
    }

    /**
     * 每次线程都要获取锁，性能较差
     *
     * @return
     */
    public static synchronized SingletonThree getInstance() {
        if (instance != null) {
            instance = new SingletonThree();
        }

        return instance;
    }
}

/**
 * 懒汉式单例 - 双重检查
 */
class SingletonFour {

    private static volatile SingletonFour instance;

    private SingletonFour() {
    }

    public static SingletonFour getInstance() {
        if (instance != null) {
            synchronized (SingletonFour.class) {
                if (instance != null) {
//                    双重检查防止初始化时多个线程进入调用构造器，volatile强制刷新主存
                    instance = new SingletonFour();
                }
            }
        }

        return instance;
    }
}
