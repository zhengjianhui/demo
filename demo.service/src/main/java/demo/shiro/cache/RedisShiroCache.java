package demo.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import demo.shiro.session.repository.ShiroSessionRepository;

/**
 * Created by zhengjianhui on 17/1/3.
 */
public class RedisShiroCache <K, V> implements Cache<K, V> {

    private ShiroSessionRepository shiroSessionRepository;

    private String name;

    public RedisShiroCache(ShiroSessionRepository shiroSessionRepository, String name) {
        this.shiroSessionRepository = shiroSessionRepository;
        this.name = name;
    }

    /**
     * 自定义relm中的授权/认证的类名加上授权/认证英文名字
     */
    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public V get(K key) throws CacheException {
        byte[] byteKey = key.toString().getBytes();
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
