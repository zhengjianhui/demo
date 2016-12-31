package demo.dao.nosql.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhengjianhui on 16/12/21.
 */
public class PubSubService {

    private JedisPool pool;

    private final Jedis jedis;

    public PubSubService(JedisPool pool) {
        this.pool = pool;
        jedis = pool.getResource();
    }

    public void handleMessage() {
        System.out.println("redis 监听启动");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    jedis.psubscribe(new MyListener(pool), "__keyevent@*__:expired");
                }
            }
        }).start();
    }

}
