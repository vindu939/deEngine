package com.talentica.doEngine.client.telegram.request;

/**
 * stas
 * 5/1/16.
 */
public class SendLocation extends AbstractSendRequest<SendLocation> {

    public SendLocation(Object chatId, float latitude, float longitude) {
        super(chatId);
        add("latitude", latitude);
        add("longitude", longitude);
    }
}
