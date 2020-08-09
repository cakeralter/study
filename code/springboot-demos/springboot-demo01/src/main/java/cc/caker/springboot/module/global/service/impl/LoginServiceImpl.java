package cc.caker.springboot.module.global.service.impl;

import cc.caker.common.vo.ResponseResult;
import cc.caker.springboot.module.global.service.LoginService;
import cc.caker.springboot.repo.mapper.db1.AdminMapper;
import cc.caker.springboot.repo.model.db1.Admin;
import cc.caker.springboot.util.EncryptUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;
import java.util.Objects;

import static cc.caker.springboot.constant.Enumerations.Status;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final AdminMapper adminMapper;

    @Override
    public ResponseResult<String> login(Admin admin) {
        if (Objects.isNull(admin) || Strings.isNullOrEmpty(admin.getUsername()) || Strings.isNullOrEmpty(admin.getPassword())) {
            return ResponseResult.fail("用户名或密码不存在");
        }
        Admin q = new Admin();
        q.setUsername(admin.getUsername());
        q.setStatus(Status.ENABLED.getValue());
        Admin origin = adminMapper.selectOne(new QueryWrapper<>(q));
        if (Objects.isNull(origin)
                || !Objects.equals(origin.getPassword(), EncryptUtils.encrypt(admin.getPassword(), origin.getSecret()))) {
            return ResponseResult.fail("用户名或密码错误");
        }
        Admin target = new Admin();
        target.setId(origin.getId());
        target.setLoginTime(LocalDateTime.now());
        adminMapper.updateById(target);

        return ResponseResult.ok(new String(Base64Utils.encode(origin.getSecret().getBytes())));
    }
}
