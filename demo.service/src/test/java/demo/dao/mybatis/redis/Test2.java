package demo.dao.mybatis.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.dao.nosql.DbTest2;

/**
 * Created by zhengjianhui on 16/12/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-redis.xml"})
public class Test2 {

    @Autowired
    private DbTest2 test2;

    @Test
    public void test11(){
        test2.addTest("db2","123");
    }
}
