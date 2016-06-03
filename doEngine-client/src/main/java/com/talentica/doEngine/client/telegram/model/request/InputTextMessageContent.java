package com.talentica.doEngine.client.telegram.model.request;

/**
 * Stas Parshin
 * 06 May 2016
 */
public class InputTextMessageContent extends InputMessageContent {

    private String message_text;
    private ParseMode parse_mode;
    private Boolean disable_web_page_preview;

    public InputTextMessageContent(String messageText) {
        this.message_text = messageText;
    }

    public InputTextMessageContent parseMode(ParseMode parseMode) {
        this.parse_mode = parseMode;
        return this;
    }

    public InputTextMessageContent disableWebPagePreview(Boolean disableWebPagePreview) {
        this.disable_web_page_preview = disableWebPagePreview;
        return this;
    }
}
