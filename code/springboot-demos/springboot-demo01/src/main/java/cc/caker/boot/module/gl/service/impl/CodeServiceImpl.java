package cc.caker.boot.module.gl.service.impl;

import cc.caker.boot.component.CustomProperties;
import cc.caker.boot.constant.Enumerations.OperationType;
import cc.caker.boot.module.gl.dto.Mail;
import cc.caker.boot.module.gl.service.CodeService;
import cc.caker.boot.module.gl.service.MailService;
import cc.caker.boot.util.BaseUtils;
import cc.caker.common.service.RedisService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static cc.caker.boot.constant.RedisConst.DEFAULT_VERIFY_CODE_KEY_EXPIRE;
import static cc.caker.boot.constant.RedisConst.GL_VERIFY_CODE;

/**
 * @author cakeralter
 * @date 2020/8/13
 */
@RequiredArgsConstructor
@Service
public class CodeServiceImpl implements CodeService {

    private final CustomProperties properties;
    private final RedisService redisService;
    private final MailService mailService;

    @Override
    public boolean send(String username, String email, OperationType type) {
        String code = createCode(username);
        if (Strings.isNullOrEmpty(code)) return false;

        Mail mail = new Mail();
        mail.setTemplateName(type.getTemplate());
        mail.setTo(new String[]{email});
        mail.setSubject(type.getName());
        Map<String, Object> map = new HashMap<>(4);
        map.put("user", username);
        map.put("type", type.getName());
        map.put("code", code);
        mail.setTemplateModel(map);
        mailService.sendTemplateMail(mail, type.getPath());

        return true;
    }

    @Override
    public boolean valid(String userInfo, String input) {
        if (Strings.isNullOrEmpty(userInfo) || Strings.isNullOrEmpty(input)) return false;
        String code = redisService.get(GL_VERIFY_CODE + "::" + userInfo);
        return input.equalsIgnoreCase(code);
    }

    /**
     * 生成验证码
     *
     * @param userInfo
     * @return
     */
    @Override
    public String createCode(String userInfo) {
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
}
