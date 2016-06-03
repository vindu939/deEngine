package com.talentica.doEngine.client.telegram.request;


import com.talentica.doEngine.client.telegram.response.BaseResponse;

/**
 * stas
 * 5/2/16.
 */
public class SendChatAction extends BaseRequest<SendChatAction, BaseResponse> {

    public SendChatAction(Object chatId, String action) {
        super(BaseResponse.class);
        add("chat_id", chatId).add("action", action);
    }
}
