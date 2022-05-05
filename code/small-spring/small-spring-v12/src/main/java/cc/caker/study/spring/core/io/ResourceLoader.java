package cc.caker.study.spring.core.io;

/**
 * 资源加载接口
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 根据路径加载资源
     *
     * @param location
     * @return
     */
    Resource getResource(String location);
}
