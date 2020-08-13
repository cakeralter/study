package cc.caker.boot.module.gl.service;

import cc.caker.boot.module.gl.dto.Mail;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cakeralter
 * @date 2020/8/11
 */
public interface MailService {

    /**
     * 发送简单邮件
     *
     * @param mail
     */
    void sendSimpleMail(Mail mail);

    /**
     * 发送带附件的邮件
     *
     * @param mail
     * @param attachments
     */
    void sendAttachmentsMail(Mail mail, MultipartFile... attachments);

    /**
     * 发送静态资源  一张照片
     *
     * @param mail
     * @throws Exception
     */
    void sendInlineMail(Mail mail);

    /**
     * 发送模板邮件
     *
     * @param mail
     * @param path
     */
    void sendTemplateMail(Mail mail, String path);
}
