package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * stas
 * 1/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InlineQuery {

    private String id;
    private User from;
    private Location location;
    private String query;
    private String offset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InlineQuery that = (InlineQuery) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        return offset != null ? offset.equals(that.offset) : that.offset == null;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /*@Override
    public String toString() {
        return "InlineQuery{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", location=" + location +
                ", query='" + query + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }*/
}
