package demo.dao.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import demo.dao.redis.DbTest2;

/**
 * Created by zhengjianhui on 16/12/18.
 */
@Component
public class Db2TestImpl implements DbTest2 {

    // @Qualifier("db2")  选择指定名字的 bean 注入
    @Autowired
    @Qualifier("db2")
    private RedisTemplate<String, String> db2RedisTemplate;


    @Override
    public String addTest(final String key, final String value) {
        String invitationCode = db2RedisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) {
                connection.set(db2RedisTemplate.getStringSerializer().serialize(key),
                        db2RedisTemplate.getStringSerializer().serialize(value));
                return value;
            }
        });

        return invitationCode;

    }

    @Override
    public void clearDB() {
        db2RedisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) {
                connection.flushDb();
                return null;
            }
        });
    }
}
