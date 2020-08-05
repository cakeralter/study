package cc.caker.common.boot.vo;

import lombok.*;

import java.io.Serializable;

import static cc.caker.common.boot.enumeration.ResponseCode.OK;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 8100914725362538840L;
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<>(OK.getCode(), "", data);
    }

    public static <T> ResponseResult<T> build(int code, String message, T data) {
        return new ResponseResult<>(code, message, data);
    }
}
