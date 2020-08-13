package cc.caker.boot.module.global.service.impl;

import cc.caker.boot.component.CustomProperties;
import cc.caker.boot.module.global.service.LoginService;
import cc.caker.boot.repo.mapper.db1.AdminMapper;
import cc.caker.boot.repo.model.db1.Admin;
import cc.caker.boot.util.BaseUtils;
import cc.caker.boot.util.EncryptUtils;
import cc.caker.common.service.RedisService;
import cc.caker.common.vo.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.time.LocalDateTime;
import java.util.Objects;

import static cc.caker.boot.constant.Enumerations.Status;
import static cc.caker.boot.constant.RedisConst.DEFAULT_VERIFY_CODE_KEY_EXPIRE;
import static cc.caker.boot.constant.RedisConst.GL_VERIFY_CODE;

/**
 * @author cakeralter
 * @date 2020/8/8
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final AdminMapper adminMapper;
    private final RedisService redisService;
    private final CustomProperties properties;

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

    @Override
    public String createVerifyCode(String userInfo) {
        if (Strings.isNullOrEmpty(userInfo)) return null;
        String key = GL_VERIFY_CODE + "::" + userInfo;
        String code = redisService.get(key);
        if (Strings.isNullOrEmpty(code)) {
            // 生成验证码
            code = BaseUtils.randomCode(properties.getVerifyCodeLength());
            redisService.put(key, code, DEFAULT_VERIFY_CODE_KEY_EXPIRE);
        }
        return code;
    }

    @Override
    public boolean validVerifyCode(String userInfo, String input) {
        if (Strings.isNullOrEmpty(userInfo) || Strings.isNullOrEmpty(input)) return false;
        String code = redisService.get(GL_VERIFY_CODE + "::" + userInfo);
        return input.equalsIgnoreCase(code);
    }
}
