package com.talentica.doEngine.client.telegram;


import com.talentica.doEngine.client.telegram.impl.FileApi;
import com.talentica.doEngine.client.telegram.impl.TelegramBotClient;
import com.talentica.doEngine.client.telegram.model.File;
import com.talentica.doEngine.client.telegram.request.BaseRequest;
import com.talentica.doEngine.client.telegram.response.BaseResponse;

/**
 * Stas Parshin
 * 16 October 2015
 */
public class TelegramBot extends OldTelegramBot {

    private final TelegramBotClient api;
    private final FileApi fileApi;

    TelegramBot(TelegramBotClient api, FileApi fileApi) {
        super(fileApi);
        this.api = api;
        this.fileApi = fileApi;
    }

    public String getFullFilePath(File file) {
        return fileApi.getFullFilePath(file.getFilePath());
    }

    @Override
    public <T extends BaseRequest, R extends BaseResponse> R execute(BaseRequest<T, R> request) {
        return api.send(request);
    }

    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(T request, Callback<T, R> callback) {
        api.send(request, callback);
    }
}
