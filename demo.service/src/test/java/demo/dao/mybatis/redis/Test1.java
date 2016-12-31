package demo.dao.mybatis.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.dao.nosql.DbTest1;

/**
 * Created by zhengjianhui on 16/12/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        {"classpath:spring/spring-redis.xml"
})
public class Test1 {

    @Autowired
    private DbTest1 test1;

    @Test
    public void test11(){
      test1.addTest("db1","123");
    }

}
