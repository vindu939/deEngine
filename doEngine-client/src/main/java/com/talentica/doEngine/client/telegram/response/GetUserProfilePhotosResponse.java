package com.talentica.doEngine.client.telegram.response;


import com.talentica.doEngine.client.telegram.model.UserProfilePhotos;

/**
 * stas
 * 8/11/15.
 */
public class GetUserProfilePhotosResponse extends BaseResponse {

    private UserProfilePhotos result;

    GetUserProfilePhotosResponse() {
    }

    public UserProfilePhotos photos() {
        return result;
    }

    @Override
    public String toString() {
        return "GetUserProfilePhotosResponse{" +
                "result=" + result +
                '}';
    }
}
