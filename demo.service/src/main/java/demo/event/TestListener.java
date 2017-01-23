package demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by zhengjianhui on 17/1/23.
 */
@Component
public class TestListener implements ApplicationListener<TestEvent> {

    @Override
    @Async
    public void onApplicationEvent(TestEvent testEvent) {
        ListnenrEventMessage message = testEvent.getListnenrEventMessage();

        System.out.println(message.getName() + "发布了：" + message.getMessage());
    }
}
