package com.talentica.doEngine.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * Created by aravindp on 30/5/16.
 */
@Service(value = "IncomingMessagePublisher")
public class Publisher {

    @Autowired
    EventBus eventBus;

    public void publishIncomingMessage(Object message) throws InterruptedException {
        eventBus.notify("IncomingMessageQueue", Event.wrap(message));
        System.out.println("Published incoming message : " + message);
    }
}
