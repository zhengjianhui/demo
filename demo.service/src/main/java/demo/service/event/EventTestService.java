package demo.service.event;

import org.springframework.stereotype.Service;

import demo.event.ListnenrEventMessage;

/**
 * Created by zhengjianhui on 17/1/23.
 */
@Service
public interface EventTestService {

    void sendEvent(ListnenrEventMessage listnenrEventMessage);

    void sendEventTransaction(ListnenrEventMessage listnenrEventMessage);
}
