package cc.caker.study.mybatis.binding;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * MapperProxy
 *
 * @author cakeralter
 * @date 2022/5/22
 * @since 1.0
 */
@AllArgsConstructor
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6990870874943733962L;
    private final Class<T> mapperInterface;
    private Map<String, String> sqlSession;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            // Object方法不走代理
            return method.invoke(this, args);
        }
        return "你的方法被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
    }
}
