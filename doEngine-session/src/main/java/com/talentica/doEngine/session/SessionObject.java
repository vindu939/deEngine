package com.talentica.doEngine.session;

/**
 * Created by aravindp on 30/5/16.
 */
public class SessionObject {

    private SessionState sessionState;
    private Object data;
    private SessionMetaData sessionMetaData;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SessionMetaData getSessionMetaData() {
        return sessionMetaData;
    }

    public void setSessionMetaData(SessionMetaData sessionMetaData) {
        this.sessionMetaData = sessionMetaData;
    }

    public SessionState getSessionState() {
        return sessionState;
    }

    public void setSessionState(SessionState sessionState) {
        this.sessionState = sessionState;
    }


}
