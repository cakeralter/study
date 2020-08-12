package cc.caker.boot.component.shiro;

import com.google.common.base.Strings;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义session获取
 *
 * @author cakeralter
 * @date 2020/8/12
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 请求头的名字
     */
    private static final String AUTHORIZATION = "Authorization";
    private static final String STATELESS_REQUEST = "Stateless request";

    @Override
    protected Serializable getSessionId(ServletRequest req, ServletResponse res) {
        String sessionId = WebUtils.toHttp(req).getHeader(AUTHORIZATION);
        // 如果请求头中有 Authorization 则其值为sessionId
        if (!Strings.isNullOrEmpty(sessionId)) {
            req.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, STATELESS_REQUEST);
            req.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            req.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, true);
            return sessionId;
        }

        // 否则按默认规则从cookie取sessionId
        return super.getSessionId(req, res);
    }
}
