package com.talentica.doEngine.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.request.SendMessage;
import com.talentica.doEngine.session.*;
import com.talentica.graphite.search.context.TextContext;
import com.talentica.graphite.search.context.UserQuerySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by aravindp on 30/5/16.
 */
@Service(value = "OutgoingMessageReceiver")
public class Receiver implements Consumer<Event<Object>>{

    @Autowired
    TelegramBot telegramBot;

    @Override
    public void accept(Event<Object> objectEvent) {
        System.out.println("Received outgoing message : " + objectEvent.getData());
        String sessionId = (String) objectEvent.getData();
        SessionObject sessionObject = SessionManager.getSession(sessionId);
        SessionMetaData sessionMetaData = sessionObject.getSessionMetaData();

        UserQuerySession uqs = null;
        try {
            uqs = (UserQuerySession) sessionObject.getData();
            TextContext context = uqs.getContext();
            System.out.println(context.getActor() + " : "
                    +context.getType() + " : " + context.getValue());

            SystemState systemState = sessionMetaData.getSystemState();
            SessionState sessionState = sessionObject.getSessionState();

            String responseData = context.getValue();
            String message = "";
            boolean updateSession = true;

            if (systemState == SystemState.QUERY){
                if (sessionState == SessionState.AUTH_INIT) {
                    message = "U need to be authenticated before proceeding so plz, ";
                }

                message = message + responseData;
            } else {
                ObjectMapper mapper = new ObjectMapper();
                Map response = mapper.readValue(responseData, Map.class);
                if(response.get("status") != null && (int) response.get("status") == 200){
                    String authData = (String) response.get("data");
                    SessionState targetSessionState = this.getTargetSessionState(sessionState, systemState);
                    if (targetSessionState == SessionState.AUTH_DONE){
                        SessionManager.addUserAuthData(sessionId, authData);
                        message = "Hi! u have been authenticated \n How may I help u?";
                    } else {
                        message = "formatted response";
                    }
                } else if ((int) response.get("status") == 600) {
                    message = (String) response.get("data");
                    if (sessionMetaData.isGoodBye()){
                        sessionMetaData.setOptedSessionType(SessionType.USER);
                        sessionMetaData.setGoodBye(false);
                    }
                } else {
                    //updateSession = false;
                    if (response.get("data") != null && !((String)response.get("data")).isEmpty()) {
                        message = (String) response.get("data");
                    } else {
                        message = "Oops! some issue with the server plz try later.";
                    }
                }
            }

            telegramBot.execute(new SendMessage(sessionMetaData.getUserId(), message));

            if (updateSession) {
                updateSessionState(sessionId);
            }
        } catch (ClassCastException e){

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** Update SessionState(SS) only if
     * 1. SS is Init and SystemState (SyS) is Query -> Process
     * 2. SS is ? and SyS in Response -> Done
     */
    private void updateSessionState(String sessionId){
        SessionObject sessionObject = SessionManager.getSession(sessionId);
        SessionMetaData sessionMetaData = sessionObject.getSessionMetaData();

        SystemState systemState = sessionMetaData.getSystemState();
        SessionState presentSessionState = sessionObject.getSessionState();
        SessionState targetSessionState = getTargetSessionState(presentSessionState, systemState);

        if (targetSessionState != null) {
            sessionObject.setSessionState(targetSessionState);
            SessionManager.updateSession(sessionId, sessionObject);
        }
    }

    private SessionState getTargetSessionState(SessionState presentSessionState, SystemState systemState) {
        SessionState targetSessionState = null;
        if(systemState == SystemState.QUERY) {
            if (presentSessionState == SessionState.AUTH_INIT) {
                targetSessionState = SessionState.AUTH_IN_PROCESS;
            } else if(presentSessionState == SessionState.REQUEST_INIT) {
                targetSessionState = SessionState.REQUEST_IN_PROCESS;
            }
        } else if(systemState == SystemState.RESPONSE) {
            if (presentSessionState == SessionState.AUTH_INIT ||
                    presentSessionState == SessionState.AUTH_IN_PROCESS) {
                targetSessionState = SessionState.AUTH_DONE;
            } else if(presentSessionState == SessionState.REQUEST_INIT ||
                    presentSessionState == SessionState.REQUEST_IN_PROCESS) {
                targetSessionState = SessionState.REQUEST_DONE;
            }
        }
        return targetSessionState;
    }
}
