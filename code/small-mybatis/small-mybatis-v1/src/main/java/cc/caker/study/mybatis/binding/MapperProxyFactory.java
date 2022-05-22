package cc.caker.study.mybatis.binding;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * MapperProxyFactory
 *
 * @author cakeralter
 * @date 2022/5/22
 * @since 1.0
 */
@RequiredArgsConstructor
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
