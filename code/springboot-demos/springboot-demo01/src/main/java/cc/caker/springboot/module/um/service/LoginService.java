package cc.caker.springboot.module.um.service;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.repo.model.db1.Admin;

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
