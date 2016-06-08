package com.talentica.doEngine.client.telegram;

import com.talentica.doEngine.client.telegram.request.BaseRequest;
import com.talentica.doEngine.client.telegram.response.BaseResponse;

import java.io.IOException;

/**
 * stas
 * 5/3/16.
 */
public interface Callback<T extends BaseRequest, R extends BaseResponse> {

    void onResponse(T request, R response);

    void onFailure(T request, IOException e);
}
