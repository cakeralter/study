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
     * 资源不存在
     */
    NOT_FIND(404),
    /**
     * 未授权
     */
    UNAUTHORIZED(401),
    /**
     * 服务器出错
     */
    ERROR(500);

    private final int code;
}
