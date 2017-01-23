package demo.service.event.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.w3c.dom.events.Event;

import demo.event.ListnenrEventMessage;
import demo.event.TestEvent;
import demo.service.event.EventTestService;

/**
 * Created by zhengjianhui on 17/1/23.
 */
@Service
public class EventTestServiceImpl implements EventTestService {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 发布事件
     * @param listnenrEventMessage
     */
    @Override
    public void sendEvent(ListnenrEventMessage listnenrEventMessage) {

        applicationContext.publishEvent(new TestEvent(listnenrEventMessage));

    }

    /**
     * 在事物提交后发布事件
     * @param listnenrEventMessage
     */
    @Override
    @Transactional
    public void sendEventTransaction(final ListnenrEventMessage listnenrEventMessage) {

        System.out.println(listnenrEventMessage.getName() + "提交了事物");

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                applicationContext.publishEvent(new TestEvent(listnenrEventMessage));
            }
        });
    }
}
