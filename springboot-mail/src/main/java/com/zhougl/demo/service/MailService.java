package com.zhougl.demo.service;

/**
 * @author zhougl
 * 2018/9/25
 **/
public interface MailService {
    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to,String subject,String content);

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendHTMLMail(String to,String subject,String content);
    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String to,String subject,String content,String filePath);

}
