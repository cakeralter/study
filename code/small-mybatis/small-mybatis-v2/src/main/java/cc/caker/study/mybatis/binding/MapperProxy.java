package cc.caker.study.mybatis.binding;

import cc.caker.study.mybatis.session.SqlSession;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MapperProxy
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
@AllArgsConstructor
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6990870874943733962L;
    private final Class<T> mapperInterface;
    private SqlSession sqlSession;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}
