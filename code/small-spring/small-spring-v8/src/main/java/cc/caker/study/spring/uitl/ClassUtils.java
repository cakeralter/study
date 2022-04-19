package cc.caker.study.spring.uitl;

/**
 * ClassUtils
 *
 * @author cakeralter
 * @date 2022/4/17
 * @since 1.0
 */
public final class ClassUtils {

    /**
     * 获取默认类加载器
     *
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            // do nothing
        }

        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
