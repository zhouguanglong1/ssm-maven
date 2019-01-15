package com.zhougl.demo.service.impl;

import com.zhougl.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author zhougl
 * 2018/9/25
 **/
@Component
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String address;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(address);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
            LOGGER.info("简单邮件发送成功");
        } catch (Exception e){
            LOGGER.error("邮件发送异常",e);
        }
    }

    @Override
    public void sendHTMLMail(String to, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(address);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);

            javaMailSender.send(mimeMessage);
            LOGGER.info("html邮件发送成功");
        } catch (MessagingException e){
            LOGGER.error("html邮件发生异常",e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content,String filePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(address);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);

            FileSystemResource resource = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,resource);

            javaMailSender.send(mimeMessage);
            LOGGER.info("带附件的邮件发送成功！");
        } catch (MessagingException e){
            LOGGER.error("发生异常",e);
        }
    }
}
