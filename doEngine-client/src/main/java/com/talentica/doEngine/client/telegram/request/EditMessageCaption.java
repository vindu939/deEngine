package com.talentica.doEngine.client.telegram.request;


import com.talentica.doEngine.client.telegram.response.BaseResponse;
import com.talentica.doEngine.client.telegram.response.SendResponse;
import com.talentica.doEngine.client.telegram.model.request.InlineKeyboardMarkup;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageCaption extends BaseRequest<EditMessageCaption, BaseResponse> {

    public EditMessageCaption(Object chatId, int messageId, String text) {
        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId).add("text", text);
    }

    public EditMessageCaption(String inlineMessageId, String text) {
        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId).add("text", text);
    }

    public EditMessageCaption caption(String caption) {
        return add("caption", caption);
    }

    public EditMessageCaption replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
