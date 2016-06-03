package com.talentica.doEngine.client.telegram.request;


import com.talentica.doEngine.client.telegram.response.BaseResponse;
import com.talentica.doEngine.client.telegram.model.request.InlineKeyboardMarkup;
import com.talentica.doEngine.client.telegram.response.SendResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageReplyMarkup extends BaseRequest<EditMessageReplyMarkup, BaseResponse> {

    public EditMessageReplyMarkup(Object chatId, int messageId, String text) {
        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId).add("text", text);
    }

    public EditMessageReplyMarkup(String inlineMessageId, String text) {
        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId).add("text", text);
    }

    public EditMessageReplyMarkup replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
