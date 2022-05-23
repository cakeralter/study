package cc.caker.study.mybatis.session;

/**
 * SqlSessionFactory
 *
 * @author cakeralter
 * @date 2022/5/23
 * @since 1.0
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
