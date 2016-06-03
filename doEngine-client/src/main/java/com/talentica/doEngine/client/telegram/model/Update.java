package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 8/4/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Update {

    @JsonProperty(value = "update_id")
    private Integer updateId;

    private Message message;

    @JsonProperty(value = "inline_query")
    private InlineQuery inlineQuery;

    @JsonProperty(value = "chosen_inline_result")
    private ChosenInlineResult chosenInlineResult;

    @JsonProperty(value = "callback_query")
    private CallbackQuery callbackQuery;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public InlineQuery getInlineQuery() {
        return inlineQuery;
    }

    public void setInlineQuery(InlineQuery inlineQuery) {
        this.inlineQuery = inlineQuery;
    }

    public ChosenInlineResult getChosenInlineResult() {
        return chosenInlineResult;
    }

    public void setChosenInlineResult(ChosenInlineResult chosenInlineResult) {
        this.chosenInlineResult = chosenInlineResult;
    }

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public void setCallbackQuery(CallbackQuery callbackQuery) {
        this.callbackQuery = callbackQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Update update = (Update) o;

        if (updateId != null ? !updateId.equals(update.updateId) : update.updateId != null) return false;
        if (message != null ? !message.equals(update.message) : update.message != null) return false;
        if (inlineQuery != null ? !inlineQuery.equals(update.inlineQuery) : update.inlineQuery != null) return false;
        if (chosenInlineResult != null ? !chosenInlineResult.equals(update.chosenInlineResult) : update.chosenInlineResult != null)
            return false;
        return callbackQuery != null ? callbackQuery.equals(update.callbackQuery) : update.callbackQuery == null;

    }

    @Override
    public int hashCode() {
        return updateId != null ? updateId.hashCode() : 0;
    }

   /* @Override
    public String toString() {
        return "Update{" +
                "update_id=" + updateId +
                ", message=" + message +
                ", inline_query=" + inlineQuery +
                ", chosen_inline_result=" + chosenInlineResult +
                ", callback_query=" + callbackQuery +
                '}';
    }*/
}
