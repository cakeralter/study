package cc.caker.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static cc.caker.common.enumeration.ResponseCode.ERROR;
import static cc.caker.common.enumeration.ResponseCode.OK;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@ToString
@Getter
@Setter
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 8100914725362538840L;
    private int code;
    private String message;
    private T data;

    private ResponseResult() {
    }

    private ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private static <T> ResponseResultBuilder<T> builder() {
        return new ResponseResultBuilder<T>();
    }

    /**
     * 构建响应对象
     *
     * @param code    响应码
     * @param message 相应信息
     * @param data    响应数据
     * @param <T>
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> build(int code, String message, T data) {
        return new ResponseResultBuilder<T>()
                .ofCode(code).ofMessage(message).ofData(data)
                .build();
    }

    /**
     * 默认响应200
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> ok() {
        return ok(null);
    }

    public static <T> ResponseResult<T> ok(T data) {
        return ok(OK.name(), data);
    }

    public static <T> ResponseResult<T> ok(String msg, T data) {
        return build(OK.getCode(), msg, data);
    }

    /**
     * 默认响应出错
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> error() {
        return error(ERROR.name());
    }

    public static <T> ResponseResult<T> error(String msg) {
        return build(ERROR.getCode(), msg, null);
    }

    /**
     * 默认响应失败
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> fail() {
        return error();
    }

    public static <T> ResponseResult<T> fail(String msg) {
        return fail(ERROR.getCode(), msg);
    }

    public static <T> ResponseResult<T> fail(int code, String msg) {
        return build(code, msg, null);
    }

    private static final class ResponseResultBuilder<T> {
        private int code;
        private String message;
        private T data;

        private ResponseResultBuilder() {
        }

        public static <T> ResponseResultBuilder<T> aResponseResult() {
            return new ResponseResultBuilder<>();
        }

        public ResponseResultBuilder<T> ofCode(int code) {
            this.code = code;
            return this;
        }

        public ResponseResultBuilder<T> ofMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseResultBuilder<T> ofData(T data) {
            this.data = data;
            return this;
        }

        public ResponseResult<T> build() {
            ResponseResult<T> responseResult = new ResponseResult<>();
            responseResult.setCode(code);
            responseResult.setMessage(message);
            responseResult.setData(data);
            return responseResult;
        }
    }
}
