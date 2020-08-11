package cc.caker.boot.module.global.controller;

import cc.caker.boot.module.global.dto.Mail;
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
import java.util.UUID;

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

    @ApiOperation("发送验证码")
    @PostMapping("/send/code")
    public ResponseResult<String> verifyCode(Mail mail) {
        mail.setTemplateName(VERIFY_CODE_TEMPLATE);
        Map<String, String> map = new HashMap<>(4);
        map.put("to", mail.getTo()[0]);
        map.put("code", UUID.randomUUID().toString());
        mail.setTemplateModel(map);
        mailService.sendTemplateMail(mail);
        return ResponseResult.ok();
    }

    @ApiOperation("发送附件")
    @PostMapping(value = "/send/attachment")
    public ResponseResult<String> sendAttachment(Mail mail, MultipartFile[] files) {
        mailService.sendAttachmentsMail(mail, files);
        return ResponseResult.ok();
    }
}
