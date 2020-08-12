package cc.caker.boot.module.global.service;

import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.common.vo.ResponseResult;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
public interface LoginService {

    /**
     * 登录验证
     *
     * @param admin
     * @return
     */
    @Deprecated
    ResponseResult<String> login(Admin admin);

    /**
     * 生成验证码
     *
     * @param userInfo 用户邮箱或者手机号
     * @return
     */
    String createVerifyCode(String userInfo);

    /**
     * 校验验证码
     *
     * @param userInfo 用户邮箱或者手机号
     * @param input    用户输入的验证码
     * @return
     */
    boolean validVerifyCode(String userInfo, String input);
}
