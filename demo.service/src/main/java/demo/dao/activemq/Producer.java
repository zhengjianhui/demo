package demo.dao.activemq;

import demo.dto.mail.Mail;

/**
 * Created by zhengjianhui on 17/4/29.
 */
public interface Producer {

    void sendMessage(final String msg);
}
