package com.talentica.doEngine.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.doEngine.client.telegram.TelegramBot;
import com.talentica.doEngine.client.telegram.request.SendMessage;
import com.talentica.doEngine.session.*;
import com.talentica.graphite.search.context.SystemQuery;
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

            /*String authData = "{" +
                    "\"access_token\": \"aa49e025-c4fe-4892-86af-15af2e6b72a2\"," +
                    "\"token_type\": \"bearer\"," +
                    "\"refresh_token\": \"97a9f978-7aad-4af7-9329-78ff2ce9962d\"," +
                    "\"expires_in\": 43199," +
                    "\"scope\": \"read write\"" +
                    "}";*/
            ObjectMapper mapper = new ObjectMapper();
            String responseData = context.getValue();
            Map response = mapper.readValue(responseData, Map.class);
            if(response.get("status") != null && (int) response.get("status") == 200){
                String authData = (String) response.get("message");
                SessionState targetSessionState = this.getTargetSessionState(sessionState, systemState);
                if (targetSessionState == SessionState.AUTH_DONE){
                    SessionManager.addUserAuthData(sessionId, authData);
                }
            } else {

            }

            if(response.get("status") == null) {
                String message = "";
                if (sessionState == SessionState.AUTH_INIT) {
                    message = "U need to be authenticated before proceeding so plz, ";
                }
                telegramBot.execute(new SendMessage(192591982, message + context.getValue()));
            }

            updateSessionState(sessionId);
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
