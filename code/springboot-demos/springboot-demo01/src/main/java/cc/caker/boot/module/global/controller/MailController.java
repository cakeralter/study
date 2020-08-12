package cc.caker.boot.module.global.controller;

import cc.caker.boot.component.limiter.RequestLimiter;
import cc.caker.boot.component.log.SysLog;
import cc.caker.boot.module.global.dto.Mail;
import cc.caker.boot.module.global.service.LoginService;
import cc.caker.boot.module.global.service.MailService;
import cc.caker.common.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static cc.caker.boot.constant.Enumerations.OperationType;
import static cc.caker.boot.constant.MailConst.VERIFY_CODE_TEMPLATE;

/**
 * @author cakeralter
 * @date 2020/8/11
 */
@RequiredArgsConstructor
@Api(tags = "邮件相关接口")
@RequestMapping("/mail")
@RestController
public class MailController {

    private final MailService mailService;
    private final LoginService loginService;

    @RequestLimiter(qps = 0.1d, message = "操作太过频繁, 请稍后再试!")
    @SysLog
    @ApiOperation("发送验证码")
    @PostMapping("/send/code")
    public ResponseResult<String> verifyCode(String username, String email) {
        Mail mail = new Mail();
        mail.setTemplateName(VERIFY_CODE_TEMPLATE);
        mail.setTo(new String[]{email});
        mail.setSubject("验证码");
        Map<String, Object> map = new HashMap<>(4);
        map.put("user", username);
        map.put("type", OperationType.REGISTER.getName());
        map.put("code", loginService.createVerifyCode(username));
        mail.setTemplateModel(map);
        mailService.sendTemplateMail(mail);
        return ResponseResult.ok();
    }

    @SysLog
    @ApiOperation("发送附件")
    @PostMapping(value = "/send/attachment")
    public ResponseResult<String> sendAttachment(Mail mail, MultipartFile[] files) {
        mailService.sendAttachmentsMail(mail, files);
        return ResponseResult.ok();
    }
}
