package demo.service.mail;

import demo.dto.mail.Mail;

/**
 * Created by zhengjianhui on 17/4/29.
 */
public interface MailService {

    void sendMessage(Mail mail);
}
