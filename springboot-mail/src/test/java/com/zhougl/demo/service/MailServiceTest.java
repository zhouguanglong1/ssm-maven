package com.zhougl.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() throws Exception {
        mailService.sendSimpleMail("970711665@qq.com","测试邮件","hello,周帅哥");
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "E:\\ZYJH-REPORT-FTP-20180821.xlsx";
        mailService.sendAttachmentsMail("970711665@qq.com","测试带附件的邮件","帅哥你好，这是你的邮件，详情看附件",filePath);
    }

    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHTMLMail("970711665@qq.com","主题：这是模板邮件",emailContent);
    }

}