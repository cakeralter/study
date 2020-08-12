package top.caker.finaltest;

/**
 * final修饰的常量可以初始化的位置有：
 * <b>显式初始化、代码块初始化、构造器初始化</b>
 *
 * @author cakeralter
 * @date 2020/7/25
 */
public class FinalTest {

    public static void main(String[] args) {
        System.out.println(new FinalObject());
    }
}

class FinalObject {

    /**
     * 显式初始化
     */
    final int a = 0;
    final int b;
    final int c;
    final int d;

    /*
     * 代码块初始化
     */ {
        b = 1;
        setB();
    }

    public FinalObject() {
        this(2, 3);
    }

    /**
     * 构造器初始化
     *
     * @param c
     * @param d
     */
    public FinalObject(int c, int d) {
        this.c = c;
        this.d = d;
    }

    public void setB() {
        // 常量不能在此处初始化
//        d = 3;
    }

    @Override
    public String toString() {
        return "FinalObject{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
