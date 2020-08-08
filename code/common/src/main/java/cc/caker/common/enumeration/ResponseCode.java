package cc.caker.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {

    /**
     * 响应成功
     */
    OK(200),
    /**
     * 未登录
     */
    UNAUTHORIZED(401),
    /**
     * 未授权拒绝访问
     */
    FORBIDDEN(403),
    /**
     * 资源不存在
     */
    NOT_FIND(404),
    /**
     * 服务器出错
     */
    SERVER_ERROR(500);

    private final int status;
}
