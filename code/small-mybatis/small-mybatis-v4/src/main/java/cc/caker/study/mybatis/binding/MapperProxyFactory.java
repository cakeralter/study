package cc.caker.study.mybatis.binding;

import cc.caker.study.mybatis.session.SqlSession;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * MapperProxyFactory
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;
    private Map<Method, MapperMethod> methodCache = Maps.newConcurrentMap();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return methodCache;
    }

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
