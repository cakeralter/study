package cc.caker.boot.module.gl.controller;

import cc.caker.boot.component.log.SysLog;
import cc.caker.boot.module.gl.dto.Mail;
import cc.caker.boot.module.gl.service.MailService;
import cc.caker.common.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @SysLog
    @ApiOperation("发送附件")
    @PostMapping(value = "/send/attachment")
    public ResponseResult<String> sendAttachment(Mail mail, MultipartFile[] files) {
        mailService.sendAttachmentsMail(mail, files);
        return ResponseResult.ok();
    }
}
