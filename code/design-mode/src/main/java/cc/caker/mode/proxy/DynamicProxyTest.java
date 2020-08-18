package cc.caker.mode.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式 - 动态代理
 *
 * @author cakeralter
 * @date 2020/7/26
 */
public class DynamicProxyTest {

    @Test
    public void test() {
        Service service = new ServiceImpl();
        Service proxy = (Service) new ProxyClass(service).getProxy();
        proxy.service1();
        proxy.service2();
    }
}

interface Service {

    void service1();

    int service2();
}

class ServiceImpl implements Service {

    @Override
    public void service1() {
        System.out.println("执行业务一");
    }

    @Override
    public int service2() {
        System.out.println("执行业务二");
        return 0;
    }
}

/**
 * 代理类
 */
class ProxyClass implements InvocationHandler {

    /**
     * 被代理对象
     */
    private final Object object;

    public ProxyClass(Object object) {
        this.object = object;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("======before invoke======");
        final Object invoke = method.invoke(object, args);
        System.out.println("======after invoke======");
        return invoke;
    }
}
