package cc.caker.boot.module.global.service.impl;

import cc.caker.boot.module.global.dto.Mail;
import cc.caker.boot.module.global.service.MailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static cc.caker.boot.constant.MailConst.TEMPLATE_PATH;
import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;
import static freemarker.template.TemplateExceptionHandler.IGNORE_HANDLER;

/**
 * @author cakeralter
 * @date 2020/8/11
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final FreeMarkerConfigurer configurer;
    private final JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 简单邮件
     *
     * @param mail
     */
    @Override
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        message.setFrom(username);
        message.setTo(mail.getTo());
        message.setCc(mail.getCc());
        sender.send(message);
    }

    /**
     * 附件
     *
     * @param mail
     * @param attachments
     */
    @Override
    public void sendAttachmentsMail(Mail mail, MultipartFile... attachments) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText());
            helper.setFrom(username);
            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            // 附件
            for (MultipartFile attachment : attachments) {
                helper.addAttachment(attachment.getName(), attachment.getResource());
            }
            sender.send(message);
        } catch (MessagingException e) {
            log.error("邮件发送出错：{}", e.getMessage());
        }
    }

    /**
     * 发送图片
     *
     * @param mail
     */
    @Override
    public void sendInlineMail(Mail mail) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mail.getSubject());
            helper.setText("<html><body><img src=\"cid:image\" ></body></html>");
            helper.setFrom(username);
            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            helper.addInline("cid:image", new File("D:/Download/wallhaven-2em38y_1920x1080.png"));
            sender.send(message);
        } catch (MessagingException e) {
            log.error("邮件发送出错：{}", e.getMessage());
        }
    }

    /**
     * 模板邮件
     *
     * @param mail
     */
    @Override
    public void sendTemplateMail(Mail mail) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(username);
            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            // 读取模板
            Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            configuration.setTemplateLoader(new ClassTemplateLoader(this.getClass().getClassLoader(), TEMPLATE_PATH));
            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            configuration.setTemplateExceptionHandler(IGNORE_HANDLER);
            Template template = configuration.getTemplate(mail.getTemplateName() + ".ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getTemplateModel());
            helper.setText(html, true);
            sender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            log.error("邮件发送出错：{}", e.getMessage());
        }
    }
}
