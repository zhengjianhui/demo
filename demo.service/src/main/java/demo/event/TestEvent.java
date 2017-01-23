package demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by zhengjianhui on 17/1/23.
 */
public class TestEvent extends ApplicationEvent {

    private ListnenrEventMessage listnenrEventMessage;

    public TestEvent(ListnenrEventMessage listnenrEventMessage) {
        super(listnenrEventMessage);
        this.listnenrEventMessage = listnenrEventMessage;
    }

    public ListnenrEventMessage getListnenrEventMessage() {
        return listnenrEventMessage;
    }

    public void setListnenrEventMessage(ListnenrEventMessage listnenrEventMessage) {
        this.listnenrEventMessage = listnenrEventMessage;
    }
}
