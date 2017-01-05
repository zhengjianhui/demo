package demo.dao.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by zhengjianhui on 17/1/2.
 */
public class TopicRedisListener implements MessageListener {

    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        System.out.println(message);
        System.out.println(body.toString());
        System.out.println(channel.toString());


    }

}
