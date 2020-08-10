package cc.caker.boot.component.limiter;

import cc.caker.common.vo.ResponseResult;
import com.google.common.util.concurrent.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 拦截器限流
 *
 * @author cakeralter
 * @date 2020/8/10
 */
@RequiredArgsConstructor
@Component
public class RequestLimiterInterceptor implements HandlerInterceptor {

    private final Map<String, RateLimiter> limiterMap = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        RequestLimiter annotation = method.getMethodAnnotation(RequestLimiter.class);
        if (Objects.isNull(annotation)) {
            return true;
        }
        // uri作为map的key
        String key = req.getRequestURI();
        if (!limiterMap.containsKey(key))
            // 创建令牌桶
            limiterMap.put(key, RateLimiter.create(annotation.qps()));
        RateLimiter limiter = limiterMap.get(key);
        // 获取令牌
        boolean acquire = limiter.tryAcquire(annotation.timeout(), annotation.unit());
        if (!acquire) {
            // 获取令牌失败
            res.setCharacterEncoding(StandardCharsets.UTF_8.name());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().write(ResponseResult.toJson(ResponseResult.fail(annotation.message())));
            return false;
        }
        return true;
    }
}
