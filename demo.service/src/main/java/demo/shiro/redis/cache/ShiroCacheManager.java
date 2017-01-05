package demo.shiro.redis.cache;

import org.apache.shiro.cache.Cache;

/**
 * Created by zhengjianhui on 17/1/2.
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();
}
