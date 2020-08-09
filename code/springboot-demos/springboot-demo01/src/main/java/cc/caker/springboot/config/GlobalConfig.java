package cc.caker.springboot.config;

import cc.caker.springboot.component.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cakeralter
 * @since 2020/8/6
 */
@Configuration
public class GlobalConfig {

    /**
     * Xss过滤
     *
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<XssFilter> filterFilterRegistrationBean(XssFilter filter) {
        FilterRegistrationBean<XssFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.setOrder(Integer.MAX_VALUE - 1);
        bean.setUrlPatterns(Collections.singletonList("/*"));
        Map<String, String> param = new HashMap<>(2);
        param.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        param.put("IS_INCLUDE_RICH_TEXT", "true");
        bean.setInitParameters(param);
//        bean.setEnabled(true);
        return bean;
    }
}
