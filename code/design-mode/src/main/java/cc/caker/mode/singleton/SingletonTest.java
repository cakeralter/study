package cc.caker.mode.singleton;

import java.util.Objects;

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
    }
}

/**
 * 饿汉式单例
 */
class SingletonOne {

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

    private static SingletonTwo instance = null;

    private SingletonTwo() {
    }

    public static SingletonTwo getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SingletonTwo();
        }

        return instance;
    }
}
