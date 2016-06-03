package com.talentica.doEngine.core;

import com.talentica.doEngine.session.*;
import com.talentica.graphite.search.context.SystemQuery;
import com.talentica.graphite.search.context.TextContext;
import com.talentica.graphite.search.context.UserQuerySession;
import com.talentica.graphite.search.exception.SearchException;
import com.talentica.graphite.store.StoreResources;
import com.talentica.graphite.store.exception.InvalidStoreException;
import com.talentica.graphite.store.exception.StoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * Created by aravindp on 30/5/16.
 */
@Service(value = "IncomingMessageReceiver")
public class Receiver implements Consumer<Event<Object>> {

    @Autowired
    @Qualifier("OutgoingMessagePublisher")
    private Publisher publisher;

    @Override
    public void accept(Event<Object> objectEvent) {
        System.out.println("Received incoming message : " + objectEvent.getData());

        String sessionId = (String) objectEvent.getData();
        SessionObject sessionObject = SessionManager.getSession(sessionId);
        SessionMetaData sessionMetaData = sessionObject.getSessionMetaData();
        UserQuerySession uqs = null;
        try {
            uqs = (UserQuerySession) sessionObject.getData();
        } catch (ClassCastException e){

        }

        if(uqs == null){
            String namespace = sessionMetaData.getNamespace();
            String businessEndpoint = sessionMetaData.getEndpoint();

            StoreResources storeResources = new StoreResources(StoreResources.StoreType.blazegraph, namespace);
            try {
                uqs = new UserQuerySession(storeResources);
                uqs.setBusinessEndpointUrl(businessEndpoint);
            } catch (SearchException e) {
                e.printStackTrace();
            } catch (StoreException e) {
                e.printStackTrace();
            } catch (InvalidStoreException e) {
                e.printStackTrace();
            }
        }

        String textMessage = sessionObject.getSessionMetaData().getMessage();

        // Find a better way to handle the commands
        if(sessionObject.getSessionState() == SessionState.AUTH_INIT){
            textMessage = "login";
            uqs.addHeader("Authorization", sessionMetaData.getBasicAuthToken());
            uqs.addHeader("Content-Type", "application/x-www-form-urlencoded");
        }

        if(sessionMetaData.isAuthRequired() && sessionMetaData.getAuthToken() != null){
            uqs.addHeader("Authorization", sessionMetaData.getAuthToken());
        }

        // process the text and generate response data
        DoEngineHandler doEngineHandler = new DoEngineHandler(uqs);
        doEngineHandler.handleTextMessage(textMessage);

        TextContext context = uqs.getContext();
        System.out.println(context.getActor() + " : "
                +context.getType() + " : " + context.getValue());

        SystemState systemState = SystemState.RESPONSE;
        if (context instanceof SystemQuery) {
            systemState = SystemState.QUERY;
        }

        sessionMetaData.setSystemState(systemState);
        // Update session
        sessionObject.setData(uqs);
        SessionManager.updateSession(sessionId, sessionObject);

        try {
            publisher.publishOutgoingMessage(sessionId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
