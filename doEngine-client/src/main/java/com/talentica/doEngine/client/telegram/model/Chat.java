package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 * stas
 * 8/5/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chat {

    public enum Type {
        @SerializedName("private")Private, group, supergroup, channel
    }

    private Long id;
    private String type;

    //Private
    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    //Private and Channel
    private String username;

    //Channel and Group
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (id != null ? !id.equals(chat.id) : chat.id != null) return false;
        if (type != chat.type) return false;
        if (firstName != null ? !firstName.equals(chat.firstName) : chat.firstName != null) return false;
        if (lastName != null ? !lastName.equals(chat.lastName) : chat.lastName != null) return false;
        if (username != null ? !username.equals(chat.username) : chat.username != null) return false;
        return title != null ? title.equals(chat.title) : chat.title == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /*@Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", type=" + type +
                ", first_name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                '}';
    }*/
}
