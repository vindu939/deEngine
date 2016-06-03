package com.talentica.doEngine.client.telegram.request;



import com.talentica.doEngine.client.telegram.response.BaseResponse;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * stas
 * 5/1/16.
 */
abstract public class BaseRequest<T extends BaseRequest, R extends BaseResponse> {

    @SuppressWarnings("unchecked")
    private final T thisAsT = (T) this;

    private final Class<? extends R> responseClass;
    private final Map<String, Object> parameters;

    public BaseRequest(Class<? extends R> responseClass) {
        this.responseClass = responseClass;
        this.parameters = new HashMap<String, Object>();
    }

    protected T add(String name, Object val) {
        parameters.put(name, val);
        return thisAsT;
    }

    public String getMethod() {
        String className = this.getClass().getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Type getResponseType() {
        return responseClass;
    }

    public boolean isMultipart() {
        return false;
    }

    public String getFileName() {
        return "file.txt";
    }

    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }
}
