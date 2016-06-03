package com.talentica.doEngine.session;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aravindp on 30/5/16.
 */
public class SessionManager {

    private static Map<String, SessionObject> session = new HashMap<>();
    private static Map<Integer, String> userSessionMap = new HashMap<>();
    private static Map<String, AuthObject> userAuthMap = new HashMap<>();

    public static String getSessionID(SessionMetaData sessionMetaData){
        Integer userId = sessionMetaData.getUserId();
        SessionType optedType = sessionMetaData.getOptedSessionType();
        addPropertiesToSessionMetadata(sessionMetaData);
        String sessionId = userSessionMap.get(userId);
        SessionObject sessionObject = session.get(sessionId);

        // If session object is available and session state is done then remove the session object
        if (sessionObject != null && (sessionObject.getSessionState() == SessionState.AUTH_DONE ||
                sessionObject.getSessionState() == SessionState.REQUEST_DONE)){
            session.remove(sessionId);
            sessionObject = null;
        }

        if (sessionObject != null){
            // Create a new session if requested for admin session even in middle of user session
            // Figure a way to implement this in client receiver, to prompt user before ending session abruptly
            if(sessionObject.getSessionState().ordinal() >= SessionState.REQUEST_INIT.ordinal()){
                if (!(optedType == SessionType.ADMIN)) {
                    session.remove(sessionId);
                    sessionId = createSessionObject(sessionMetaData);
                }
            } else {
                sessionObject.getSessionMetaData().setMessage(sessionMetaData.getMessage());
                updateSession(sessionId, sessionObject);
            }
        } else {
            sessionId = createSessionObject(sessionMetaData);
        }

        return sessionId;
    }

    private static void addPropertiesToSessionMetadata(SessionMetaData sessionMetaData) {
        SessionType optedType = sessionMetaData.getOptedSessionType();
        if (optedType == SessionType.USER) {
            sessionMetaData.setEndpoint(PropertiesHandler.getProperty("user.server"));
            sessionMetaData.setAuthRequired(PropertiesHandler.getProperty("user.server.isAuthRequired").equals("true"));
        } else if (optedType == SessionType.ADMIN) {
            sessionMetaData.setEndpoint(PropertiesHandler.getProperty("admin.server"));
            sessionMetaData.setAuthRequired(PropertiesHandler.getProperty("admin.server.isAuthRequired").equals("true"));
        }
        if (PropertiesHandler.getProperty("client_id") != null && PropertiesHandler.getProperty("client_secret") !=null){
            String auth = PropertiesHandler.getProperty("client_id") + ":" + PropertiesHandler.getProperty("client_secret");
            String encoding = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("ISO-8859-1")));
            String basicAuthToken = "Basic " + new String(encoding);
            sessionMetaData.setBasicAuthToken(basicAuthToken);
        }
    }

    private static String createSessionObject(SessionMetaData sessionMetaData){
        Integer userId = sessionMetaData.getUserId();
        SessionType optedType = sessionMetaData.getOptedSessionType();

        AuthObject authObject = userAuthMap.get(userId + "_" + optedType);
        SessionObject sessionObject = new SessionObject();

        sessionMetaData.setNamespace(PropertiesHandler.getProperty("default.namespace"));
        if (sessionMetaData.isAuthRequired() &&  authObject == null){
            sessionObject.setSessionState(SessionState.AUTH_INIT);
        } else {
            if (optedType == SessionType.USER){
                sessionMetaData.setNamespace(PropertiesHandler.getProperty("opted.namespace"));
            }
            if (authObject != null) {
                sessionMetaData.setAuthToken(authObject.getTokenType() + " " + authObject.getAccessToken());
            }
            sessionObject.setSessionState(SessionState.REQUEST_INIT);
        }

        sessionObject.setSessionMetaData(sessionMetaData);

        String sessionId = sessionObject.hashCode() + "";
        session.put(sessionId, sessionObject);
        userSessionMap.put(userId, sessionId);
        return sessionId;
    }

    public static SessionObject getSession(String sessionId){
        return session.get(sessionId);
    }

    public static void updateSession(String sessionId, SessionObject sessionObject){
        session.put(sessionId, sessionObject);
    }

    public static void removeSession(String sessionId){
        session.remove(sessionId);
    }

    public static void addUserAuthData(String sessionId, String authData) throws IOException {
        SessionObject sessionObject = SessionManager.getSession(sessionId);
        SessionMetaData sessionMetaData = sessionObject.getSessionMetaData();

        int userId =  sessionMetaData.getUserId();
        SessionType sessionType = sessionMetaData.getOptedSessionType();

        ObjectMapper objectMapper = new ObjectMapper();
        AuthObject authObject = objectMapper.readValue(authData, AuthObject.class);
        userAuthMap.put(userId+"_"+sessionType, authObject);
    }
}
