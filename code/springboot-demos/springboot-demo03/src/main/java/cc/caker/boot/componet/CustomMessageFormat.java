package cc.caker.boot.componet;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;


/**
 * p6spy自定义输出
 *
 * @author cakeralter
 * @since 2020/8/6
 */
public class CustomMessageFormat implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return now + " | took " + elapsed + "ms | " + sql;
    }
}
