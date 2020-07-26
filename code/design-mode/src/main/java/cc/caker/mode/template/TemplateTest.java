package cc.caker.mode.template;

/**
 * 模板方法模式
 * <p>
 * 定义好算法的步骤，每一步的细节交给具体类实现
 *
 * @author cakeralter
 * @date 2020/7/26
 */
public class TemplateTest {

    public static void main(String[] args) {
        Template template = new Product();
        template.execute();
    }
}

abstract class Template {

    public final void execute() {
        stepOne();
        stepTwo();
    }

    protected abstract void stepOne();

    protected abstract void stepTwo();
}

class Product extends Template {

    @Override
    public void stepOne() {
        System.out.println("Product.stepOne()");
    }

    @Override
    public void stepTwo() {
        System.out.println("Product.stepTwo()");
    }
}
