package cc.caker.boot.module.gl.service.impl;

import cc.caker.boot.module.gl.dto.Mail;
import cc.caker.boot.module.gl.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author cakeralter
 * @date 2020/8/11
 */
@Async
@Slf4j
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final TemplateEngine engine;
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
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText());
            helper.setFrom(username);
            helper.setTo(mail.getTo());
            helper.setCc(mail.getCc());
            // 附件
            for (MultipartFile attachment : attachments) {
                helper.addAttachment(MimeUtility.encodeWord("附件：" + attachment.getOriginalFilename(), StandardCharsets.UTF_8.name(), "B"), attachment);
            }
            sender.send(message);
        } catch (MessagingException | IOException e) {
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
     * @param path
     */
    @Override
    public void sendTemplateMail(Mail mail, String path) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(username);
            helper.setTo(mail.getTo());
            helper.setCc(new String[]{username});
            // 读取模板
            Context context = new Context();
            context.setVariables(mail.getTemplateModel());
            String html = engine.process(path + mail.getTemplateName(), context);
            helper.setText(html, true);
            sender.send(message);
        } catch (MessagingException e) {
            log.error("邮件发送出错：{}", e.getMessage());
        }
    }
}
