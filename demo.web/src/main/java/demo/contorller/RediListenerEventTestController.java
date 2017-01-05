package demo.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import demo.dao.redis.DbTest1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by zhengjianhui on 16/12/31.
 */
@Api("redis测试")
@RequestMapping
@RestController
public class RediListenerEventTestController {

    @Autowired
    private DbTest1 dbTest1;


    @ApiOperation("redis测试")
    @RequestMapping(value = "/redis/test", method = RequestMethod.GET)
    public void test() {

        for (Integer i = 0; i < 10000; i++) {
            dbTest1.addTest(i.toString(), "");
        }

        System.out.println(new Date().getTime());
    }

}
