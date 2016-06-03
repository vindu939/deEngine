package com.talentica.doEngine.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * Created by aravindp on 30/5/16.
 */
@Service(value = "OutgoingMessagePublisher")
public class Publisher {

    @Autowired
    EventBus eventBus;

    public void publishOutgoingMessage(Object message) throws InterruptedException {
        eventBus.notify("OutgoingMessageQueue", Event.wrap(message));
        System.out.println("Published outgoing message : " + message);
    }
}
