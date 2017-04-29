package demo.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.mail.Mail;
import demo.service.mail.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by zhengjianhui on 17/4/29.
 */
@Api("ActiveMQ测试")
@RequestMapping
@RestController
public class ActiveMQController {

    @Autowired
    private MailService mailService;


    @ApiOperation("activemq测试")
    @RequestMapping(value = "/mqs/sendMassage", method = RequestMethod.POST)
    public void sendMassage(@ApiParam(name = "mail", value = "假设邮件对象", required = true) @RequestBody Mail mail) {

        mailService.sendMessage(mail);
    }

}
