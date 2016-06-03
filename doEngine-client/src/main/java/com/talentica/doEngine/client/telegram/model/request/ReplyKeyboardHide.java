package com.talentica.doEngine.client.telegram.model.request;

/**
 * stas
 * 8/4/15.
 */
public class ReplyKeyboardHide extends Keyboard {

    private final boolean hide_keyboard = true;
    private final boolean selective;

    public ReplyKeyboardHide() {
        this(false);
    }

    public ReplyKeyboardHide(boolean selective) {
        this.selective = selective;
    }
}
