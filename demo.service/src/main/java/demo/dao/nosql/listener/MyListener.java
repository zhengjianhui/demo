package demo.dao.nosql.listener;

import java.util.Date;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by zhengjianhui on 16/12/30.
 */
public class MyListener extends JedisPubSub {


    private JedisPool pool;

    private volatile int test = 0;


    public MyListener(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public void onMessage(String s, String s1) {

    }

    // 取得按表达式的方式订阅的消息后的处理
    @Override
    public void onPMessage(String s, String s1, String s2) {
////        System.out.println(s + s1 + s2);
//        System.out.println("key:" + s2);
////        if(s2.equals("321")) {
////            System.out.println(11111111);
////        }

        if(s2 != null) {
            test += 1;
        }


        if(test == 10000) {
            System.out.println(test);
            System.out.println(new Date().getTime());
        }



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
