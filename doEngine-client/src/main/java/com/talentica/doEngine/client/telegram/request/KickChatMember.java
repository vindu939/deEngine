package com.talentica.doEngine.client.telegram.request;

import com.talentica.doEngine.client.telegram.response.BaseResponse;

/**
 * stas
 * 5/2/16.
 */
public class KickChatMember extends BaseRequest<KickChatMember, BaseResponse> {

    public KickChatMember(Object chatId, int userId) {
        super(BaseResponse.class);
        add("chat_id", chatId).add("user_id", userId);
    }
}
