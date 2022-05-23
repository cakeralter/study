package cc.caker.study.mybatis.binding;

import cc.caker.study.mybatis.session.SqlSession;
import lombok.AllArgsConstructor;

import java.lang.reflect.Proxy;

/**
 * MapperProxyFactory
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
@AllArgsConstructor
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
