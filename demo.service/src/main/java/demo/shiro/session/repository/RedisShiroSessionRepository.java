package demo.shiro.session.repository;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import demo.shiro.utils.SerializableUtils;


/**
 * Created by zhengjianhui on 17/1/1.
 */
public class RedisShiroSessionRepository implements ShiroSessionRepository {

    public static final String REDIS_SHIRO_SESSION = "shiro-demo-session:";

    @Autowired
    @Qualifier("shiroListenerTemplate")
    private RedisTemplate<String, String> customShiroCache;


    @Override
    public void saveSession(final Session session) {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }

        final String key = session.getId().toString();
        final byte[] value = SerializableUtils.serialize(session);

        customShiroCache.execute(new RedisCallback<String>() {

            public String doInRedis(RedisConnection connection) {
                connection.set(key.getBytes(), value);
                long timeOut = session.getTimeout() / 1000;
                connection.expire(key.getBytes(), timeOut);
                return null;
            }
        });

    }

    @Override
    public void deleteSession(Serializable sessionId) {
        if (sessionId == null) {
            throw new NullPointerException("session id is empty");
        }

        customShiroCache.delete(sessionId.toString());

    }

    @Override
    public Session getSession(final Serializable sessionId) {

        return customShiroCache.execute(new RedisCallback<Session>() {
            @Override
            public Session doInRedis(RedisConnection connection) throws DataAccessException {
                String key = sessionId.toString();
                byte[] value = connection.get(key.getBytes());
                return (Session) SerializableUtils.deserialize(value);
            }
        });

    }

    @Override
    public Collection<Session> getAllSessions() {

        customShiroCache.execute(new RedisCallback<Session>() {

            @Override
            public Session doInRedis(RedisConnection connection) throws DataAccessException {
                return null;
            }
        });


        return null;
    }

}
