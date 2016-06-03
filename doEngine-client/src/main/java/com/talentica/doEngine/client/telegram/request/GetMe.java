package com.talentica.doEngine.client.telegram.request;


import com.talentica.doEngine.client.telegram.response.GetMeResponse;

/**
 * stas
 * 5/1/16.
 */
public class GetMe extends BaseRequest<GetMe, GetMeResponse> {

    public GetMe() {
        super(GetMeResponse.class);
    }
}
