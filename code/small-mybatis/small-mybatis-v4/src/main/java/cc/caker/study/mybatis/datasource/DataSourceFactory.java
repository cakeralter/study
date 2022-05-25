package cc.caker.study.mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * DatasourceFactory
 *
 * @author cakeralter
 * @date 2022/5/25
 * @since 1.0
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}
