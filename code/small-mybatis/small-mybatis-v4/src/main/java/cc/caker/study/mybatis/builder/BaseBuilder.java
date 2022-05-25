package cc.caker.study.mybatis.builder;

import cc.caker.study.mybatis.session.Configuration;
import cc.caker.study.mybatis.type.TypeAliasRegistry;

/**
 * BaseBuilder
 *
 * @author cakeralter
 * @date 2022/5/24
 * @since 1.0
 */
public abstract class BaseBuilder {

    protected final Configuration configuration;
    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
