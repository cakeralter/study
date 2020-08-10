package cc.caker.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import static cc.caker.common.enumeration.ResponseCode.OK;
import static cc.caker.common.enumeration.ResponseCode.SERVER_ERROR;

/**
 * @author cakeralter
 * @date 2020/7/23
 */
@ToString
@Getter
@Setter
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 8100914725362538840L;
    private int status;
    private String message;
    private boolean ok;
    private T data;

    private ResponseResult() {
    }

    private ResponseResult(int status, String message, boolean ok, T data) {
        this.status = status;
        this.message = message;
        this.ok = ok;
        this.data = data;
    }

    /**
     * to json
     *
     * @param result
     * @return
     */
    public static String toJson(ResponseResult<?> result) {
        return "{" +
                "\"status\": " + result.status +
                ", \"ok\": " + result.ok +
                ", \"message\": \"" + result.message +
                "\"}";
    }

    private static <T> ResponseResultBuilder<T> builder() {
        return new ResponseResultBuilder<T>();
    }

    /**
     * 构建响应对象
     *
     * @param status  响应码
     * @param message 相应信息
     * @param ok      是否成功
     * @param data    响应数据
     * @param <T>
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> build(int status, String message, boolean ok, T data) {
        return new ResponseResultBuilder<T>()
                .ofStatus(status).ofMessage(message).ofOk(ok).ofData(data)
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
        return build(OK.getStatus(), msg, true, data);
    }

    /**
     * 默认响应出错
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> error() {
        return error(SERVER_ERROR.name());
    }

    public static <T> ResponseResult<T> error(String msg) {
        return build(SERVER_ERROR.getStatus(), msg, false, null);
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
        return fail(SERVER_ERROR.getStatus(), msg);
    }

    public static <T> ResponseResult<T> fail(int status, String msg) {
        return build(status, msg, false, null);
    }

    private static final class ResponseResultBuilder<T> {
        private int status;
        private String message;
        private boolean ok;
        private T data;

        private ResponseResultBuilder() {
        }

        public static <T> ResponseResultBuilder<T> aResponseResult() {
            return new ResponseResultBuilder<>();
        }

        public ResponseResultBuilder<T> ofStatus(int status) {
            this.status = status;
            return this;
        }

        public ResponseResultBuilder<T> ofMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseResultBuilder<T> ofOk(boolean ok) {
            this.ok = ok;
            return this;
        }

        public ResponseResultBuilder<T> ofData(T data) {
            this.data = data;
            return this;
        }

        public ResponseResult<T> build() {
            ResponseResult<T> responseResult = new ResponseResult<>();
            responseResult.setStatus(status);
            responseResult.setMessage(message);
            responseResult.setOk(ok);
            responseResult.setData(data);
            return responseResult;
        }
    }
}
