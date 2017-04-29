package demo.dao.activemq.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import demo.basicxception.DemoException;
import demo.dao.activemq.Producer;
import demo.dto.mail.Mail;
import demo.util.JSONUtil.JSONUtil;

/**
 * Created by zhengjianhui on 17/4/29.
 */
@Component
public class ProducerImpl implements Producer {

    @Autowired
    @Qualifier("jmsTemplate")
    private JmsTemplate jmsTemplate;


    @Override
    public void sendMessage(String msg) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
