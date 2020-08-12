package cc.caker.boot.component.shiro;

import cc.caker.boot.module.um.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * 动态刷新权限
 *
 * @author cakeralter
 * @date 2020/8/8
 */
@RequiredArgsConstructor
@Component
public class PermissionFlushListener {

    private final ResourceService resourceService;
    private final ShiroFilterFactoryBean factoryBean;

    @EventListener(PermissionFlushEvent.class)
    public void flush() {
        synchronized (PermissionFlushListener.class) {
            AbstractShiroFilter filter = null;
            try {
                filter = (AbstractShiroFilter) factoryBean.getObject();

                if (Objects.isNull(filter)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                throw new RuntimeException("Shiro权限刷新出错");
            }
            PathMatchingFilterChainResolver chainResolver =
                    (PathMatchingFilterChainResolver) filter.getFilterChainResolver();
            DefaultFilterChainManager manager =
                    (DefaultFilterChainManager) chainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            factoryBean.getFilterChainDefinitionMap().clear();
            factoryBean.setFilterChainDefinitionMap(resourceService.loadAllResources());
            // 重新构建生成
            Map<String, String> chains = factoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
    }
}
