package com.talentica.doEngine.client.telegram.request;

import java.io.File;

/**
 * stas
 * 5/1/16.
 */
public class SendVoice extends AbstractMultipartRequest<SendVoice> {

    public SendVoice(Object chatId, String voice) {
        super(chatId, voice);
    }

    public SendVoice(Object chatId, File voice) {
        super(chatId, voice);
    }

    public SendVoice(Object chatId, byte[] voice) {
        super(chatId, voice);
    }

    public SendVoice duration(int duration) {
        return add("duration", duration);
    }

    @Override
    protected String getFileParamName() {
        return "voice";
    }

    @Override
    public String getFileName() {
        return ContentTypes.VOICE_FILE_NAME;
    }

    @Override
    public String getContentType() {
        return ContentTypes.VOICE_MIME_TYPE;
    }
}
