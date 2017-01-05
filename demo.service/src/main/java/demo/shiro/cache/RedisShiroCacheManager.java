package demo.shiro.cache;

import org.apache.shiro.cache.Cache;

import demo.shiro.redis.cache.ShiroCacheManager;

/**
 * Created by zhengjianhui on 17/1/3.
 */
public class RedisShiroCacheManager implements ShiroCacheManager {

//    private JedisManager jedisManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
//        return new JedisShiroCache<K, V>(name, getJedisManager());
        return null;
    }

    @Override
    public void destroy() {
        //如果和其他系统，或者应用在一起就不能关闭
        //getJedisManager().getJedis().shutdown();
    }


}
