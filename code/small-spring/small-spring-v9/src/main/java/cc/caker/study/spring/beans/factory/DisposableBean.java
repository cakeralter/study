package cc.caker.study.spring.beans.factory;

/**
 * DisposableBean
 *
 * @author cakeralter
 * @date 2022/4/19
 * @since 1.0
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
