package com.talentica.doEngine.client.telegram.request;


import com.talentica.doEngine.client.telegram.model.request.InlineKeyboardMarkup;
import com.talentica.doEngine.client.telegram.model.request.ParseMode;
import com.talentica.doEngine.client.telegram.response.BaseResponse;
import com.talentica.doEngine.client.telegram.response.SendResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageText extends BaseRequest<EditMessageText, BaseResponse> {

    public EditMessageText(Object chatId, int messageId, String text) {
        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId).add("text", text);
    }

    public EditMessageText(String inlineMessageId, String text) {
        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId).add("text", text);
    }

    public EditMessageText parseMode(ParseMode parseMode) {
        return add("parse_mode", parseMode.name());
    }

    public EditMessageText disableWebPagePreview(boolean disableWebPagePreview) {
        return add("disable_web_page_preview", disableWebPagePreview);
    }

    public EditMessageText replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
