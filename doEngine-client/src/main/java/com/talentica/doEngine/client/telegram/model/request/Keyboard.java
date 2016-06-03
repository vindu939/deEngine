package com.talentica.doEngine.client.telegram.model.request;

import com.google.gson.Gson;

/**
 * stas
 * 8/11/15
 */
public abstract class Keyboard {

    // todo remove gson
    private static Gson gson = new Gson();

    @Override
    public final String toString() {
        return gson.toJson(this);
    }

}
