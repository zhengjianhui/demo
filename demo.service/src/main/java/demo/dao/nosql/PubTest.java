package demo.dao.nosql;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by zhengjianhui on 16/12/21.
 */
@Component
public class PubTest {

    @Autowired
    @Qualifier("listenerTemplate")
    private RedisTemplate<String, String> listenerTemplate;

    public void addTest(final String key, final String value) {
        listenerTemplate.convertAndSend("java", value);
    }
}
