package cc.caker.study.mybatis.builder;

import cc.caker.study.mybatis.session.Configuration;

/**
 * BaseBuilder
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
