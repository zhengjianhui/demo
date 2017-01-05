package demo.dao.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import demo.dao.redis.DbTest1;

/**
 * Created by zhengjianhui on 16/12/18.
 */
@Component
public class Db1TestImpl implements DbTest1 {

    // @Qualifier("db2")  选择指定名字的 bean 注入
    @Autowired
    @Qualifier("listenerTemplate")
    private RedisTemplate<String, String> db1RedisTemplate;


    @Override
    public String addTest(final String key, final String value) {
        String invitationCode = db1RedisTemplate.execute(new RedisCallback<String>() {

            public String doInRedis(RedisConnection connection) {
                connection.set(db1RedisTemplate.getStringSerializer().serialize(key),
                        db1RedisTemplate.getStringSerializer().serialize(value));

                connection.expire(db1RedisTemplate.getStringSerializer().serialize(key), 20);
                return key;
            }
        });

        return invitationCode;

    }

    @Override
    public void clearDB() {
        db1RedisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) {
                connection.flushDb();
                return null;
            }
        });
    }
}
