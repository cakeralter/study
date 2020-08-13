package cc.caker.boot.module.gl.service;

import cc.caker.boot.constant.Enumerations.OperationType;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
public interface CodeService {

    /**
     * 发送验证码
     *
     * @param username
     * @param email
     * @param type
     * @return
     */
    boolean send(String username, String email, OperationType type);

    /**
     * 校验验证码
     *
     * @param userInfo
     * @param input
     * @return
     */
    boolean valid(String userInfo, String input);

    /**
     * 生成验证码
     *
     * @param userInfo
     * @return
     */
    String createCode(String userInfo);
}
