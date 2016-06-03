package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 1/20/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChosenInlineResult {

    @JsonProperty(value = "result_id")
    private String resultId;

    private User from;

    private Location location;

    @JsonProperty(value = "inline_message_id")
    private String inlineMessageId;

    private String query;

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public void setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChosenInlineResult that = (ChosenInlineResult) o;

        if (resultId != null ? !resultId.equals(that.resultId) : that.resultId != null) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (inlineMessageId != null ? !inlineMessageId.equals(that.inlineMessageId) : that.inlineMessageId != null)
            return false;
        return query != null ? query.equals(that.query) : that.query == null;

    }

    @Override
    public int hashCode() {
        return resultId != null ? resultId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "ChosenInlineResult{" +
                "resultId='" + resultId + '\'' +
                ", from=" + from +
                ", location=" + location +
                ", inlineMessageId='" + inlineMessageId + '\'' +
                ", query='" + query + '\'' +
                '}';
    }*/
}
