package demo.dao.nosql.listener;

import java.util.Date;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by zhengjianhui on 16/12/30.
 */

/**
 * jedis 订阅需要继承 发布订阅类
 */
public class MyListener extends JedisPubSub {


    private JedisPool pool;


    public MyListener(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public void onMessage(String s, String s1) {

    }

    /**
     *
     * @param pattern
     * @param channel
     * @param message
     *
     *  psubscribe __keyevent@*__:expired key
     */
    // 取得按表达式的方式订阅的消息后的处理
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("模式：" + pattern + channel + message);
        System.out.println("渠道：" + channel);
        System.out.println("消息：" + message);

        // psubscribe __keyevent@*__:expired key

        // 模式：__keyevent@*__:expired__keyevent@0__:expiredkey   模式为监听所有库中的 dbindex 0 发来的消息
        // 渠道：__keyevent@0__:expired    发送的数据库
        // 消息：key       订阅的消息


//        System.out.println("key:" + s2);
////        if(s2.equals("321")) {
////            System.out.println(11111111);
////        }

    }

    @Override
    public void onSubscribe(String s, int i) {

    }

    @Override
    public void onUnsubscribe(String s, int i) {

    }

    @Override
    public void onPUnsubscribe(String s, int i) {

    }

    @Override
    public void onPSubscribe(String s, int i) {
//        System.out.println("key:" + s);
    }
}
