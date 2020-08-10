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
}
