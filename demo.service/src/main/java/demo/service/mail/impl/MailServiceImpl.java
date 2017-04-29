package demo.service.mail.impl;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.activemq.Producer;
import demo.dto.mail.Mail;
import demo.service.mail.MailService;

/**
 * Created by zhengjianhui on 17/4/29.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private Producer producer;

    @Override
    public void sendMessage(Mail mail) {
        Gson gson = new Gson();
        producer.sendMessage(gson.toJson(mail));
    }
}
