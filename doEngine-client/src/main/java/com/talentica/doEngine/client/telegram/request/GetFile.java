package com.talentica.doEngine.client.telegram.request;


import com.talentica.doEngine.client.telegram.response.GetFileResponse;

/**
 * stas
 * 5/1/16.
 */
public class GetFile extends BaseRequest<GetFile, GetFileResponse> {

    public GetFile(String fileId) {
        super(GetFileResponse.class);
        add("file_id", fileId);
    }
}
