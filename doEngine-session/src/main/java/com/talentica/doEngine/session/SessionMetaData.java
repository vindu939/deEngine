package com.talentica.doEngine.session;

/**
 * Created by aravindp on 31/5/16.
 */
public class SessionMetaData {
    private Integer userId;

    private String message;

    private SessionType optedSessionType;

    private String clientType;

    private String namespace;

    private String endpoint;

    private boolean isAuthRequired;

    private String authToken;

    private String basicAuthToken;

    private SystemState systemState;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SessionType getOptedSessionType() {
        return optedSessionType;
    }

    public void setOptedSessionType(SessionType optedSessionType) {
        this.optedSessionType = optedSessionType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public boolean isAuthRequired() {
        return isAuthRequired;
    }

    public void setAuthRequired(boolean authRequired) {
        isAuthRequired = authRequired;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getBasicAuthToken() {
        return basicAuthToken;
    }

    public void setBasicAuthToken(String basicAuthToken) {
        this.basicAuthToken = basicAuthToken;
    }

    public SystemState getSystemState() {
        return systemState;
    }

    public void setSystemState(SystemState systemState) {
        this.systemState = systemState;
    }
}
