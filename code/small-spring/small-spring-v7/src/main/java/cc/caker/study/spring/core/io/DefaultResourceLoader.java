package cc.caker.study.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DefaultResourceLoader
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public class DefaultResourceLoader implements ResourceLoader {

    /**
     * 加载资源
     *
     * @param location
     * @return
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 根据路径前缀判断是否是类路径
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }

        try {
            // 尝试使用网络加载
            return new UrlResource(new URL(location));
        } catch (MalformedURLException e) {
            // 网络加载失败尝试使用文件系统加载
            return new FileSystemResource(location);
        }
    }
}
