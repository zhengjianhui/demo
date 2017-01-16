package demo.shiro.cache.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;

import demo.shiro.utils.SerializableUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by zhengjianhui on 17/1/6.
 */
public class JedisManager {

    private JedisPool jedisPool;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (Exception e) {
            throw new JedisConnectionException(e);
        }
        return jedis;
    }

    public void returnResource(Jedis jedis, boolean isBroken) {
        if (jedis == null)
            return;
        /**
         * @deprecated starting from Jedis 3.0 this method will not be exposed. Resource cleanup should be done
         *             using @see {@link redis.clients.jedis.Jedis#close()} if (isBroken){
         *             getJedisPool().returnBrokenResource(jedis); }else{ getJedisPool().returnResource(jedis); }
         */
        jedis.close();
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            Long result = jedis.del(key);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            isBroken = true;
            throw e;
        } finally {
            returnResource(jedis, isBroken);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 获取所有Session
     * 
     * @param dbIndex
     * @param redisShiroSession
     * @return
     * @throws Exception
     */
    // @SuppressWarnings("unchecked")
    // public Collection<Session> AllSession(int dbIndex, String redisShiroSession) throws Exception {
    // Jedis jedis = null;
    // boolean isBroken = false;
    // Set<Session> sessions = new HashSet<Session>();
    // try {
    // jedis = getJedis();
    // jedis.select(dbIndex);
    //
    // Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());
    // if (byteKeys != null && byteKeys.size() > 0) {
    // for (byte[] bs : byteKeys) {
    // Session obj = SerializeUtil.deserialize(jedis.get(bs),
    // Session.class);
    // if(obj instanceof Session){
    // sessions.add(obj);
    // }
    // }
    // }
    // } catch (Exception e) {
    // isBroken = true;
    // throw e;
    // } finally {
    // returnResource(jedis, isBroken);
    // }
    // return sessions;
    // }
}
