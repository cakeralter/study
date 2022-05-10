package cc.caker.study.spring.core.io;

import cc.caker.study.spring.uitl.ClassUtils;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPathResource
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public class ClassPathResource implements Resource {

    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = ObjectUtil.defaultIfNull(classLoader, ClassUtils.getDefaultClassLoader());
    }

    /**
     * 加载类路径下的资源
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(path + "cannot be opened because it does not exist");
        }
        return is;
    }
}
