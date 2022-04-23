package cc.caker.study.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public interface Resource {

    /**
     * 获取资源流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
