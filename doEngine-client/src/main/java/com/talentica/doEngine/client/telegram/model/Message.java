package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * stas
 * 8/4/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    @JsonProperty(value = "message_id")
    private Integer messageId;

    private User from;

    private Integer date;

    private Chat chat;

    @JsonProperty(value = "forward_from")
    private User forwardFrom;

    @JsonProperty(value = "forward_from_chat")
    private Chat forwardFromChat;

    @JsonProperty(value = "forward_date")
    private Integer forwardDate;

    @JsonProperty(value = "reply_to_message")
    private Message replyToMessage;

    private String text;

    private MessageEntity[] entities;

    private Audio audio;

    private Document document;

    private PhotoSize[] photo;

    private Sticker sticker;

    private Video video;

    private Voice voice;

    private String caption;

    private Contact contact;

    private Location location;

    private Venue venue;

    @JsonProperty(value = "new_chat_member")
    private User newChatMember;

    @JsonProperty(value = "left_chat_member")
    private User leftChatMember;

    @JsonProperty(value = "new_chat_title")
    private String newChatTitle;

    @JsonProperty(value = "new_chat_photo")
    private PhotoSize[] newChatPhoto;

    @JsonProperty(value = "delete_chat_photo")
    private Boolean deleteChatPhoto;

    @JsonProperty(value = "group_chat_created")
    private Boolean groupChatCreated;

    @JsonProperty(value = "supergroup_chat_created")
    private Boolean supergroupChatCreated;

    @JsonProperty(value = "channel_chat_created")
    private Boolean channelChatCreated;

    @JsonProperty(value = "migrate_to_chat_id")
    private Long migrateToChatId;

    @JsonProperty(value = "migrate_from_chat_id")
    private Long migrateFromChatId;

    @JsonProperty(value = "pinned_message")
    private Message pinnedMessage;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public void setForwardFrom(User forwardFrom) {
        this.forwardFrom = forwardFrom;
    }

    public Chat getForwardFromChat() {
        return forwardFromChat;
    }

    public void setForwardFromChat(Chat forwardFromChat) {
        this.forwardFromChat = forwardFromChat;
    }

    public Integer getForwardDate() {
        return forwardDate;
    }

    public void setForwardDate(Integer forwardDate) {
        this.forwardDate = forwardDate;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public void setReplyToMessage(Message replyToMessage) {
        this.replyToMessage = replyToMessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageEntity[] getEntities() {
        return entities;
    }

    public void setEntities(MessageEntity[] entities) {
        this.entities = entities;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public PhotoSize[] getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoSize[] photo) {
        this.photo = photo;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public void setSticker(Sticker sticker) {
        this.sticker = sticker;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public User getNewChatMember() {
        return newChatMember;
    }

    public void setNewChatMember(User newChatMember) {
        this.newChatMember = newChatMember;
    }

    public User getLeftChatMember() {
        return leftChatMember;
    }

    public void setLeftChatMember(User leftChatMember) {
        this.leftChatMember = leftChatMember;
    }

    public String getNewChatTitle() {
        return newChatTitle;
    }

    public void setNewChatTitle(String newChatTitle) {
        this.newChatTitle = newChatTitle;
    }

    public PhotoSize[] getNewChatPhoto() {
        return newChatPhoto;
    }

    public void setNewChatPhoto(PhotoSize[] newChatPhoto) {
        this.newChatPhoto = newChatPhoto;
    }

    public Boolean getDeleteChatPhoto() {
        return deleteChatPhoto;
    }

    public void setDeleteChatPhoto(Boolean deleteChatPhoto) {
        this.deleteChatPhoto = deleteChatPhoto;
    }

    public Boolean getGroupChatCreated() {
        return groupChatCreated;
    }

    public void setGroupChatCreated(Boolean groupChatCreated) {
        this.groupChatCreated = groupChatCreated;
    }

    public Boolean getSupergroupChatCreated() {
        return supergroupChatCreated;
    }

    public void setSupergroupChatCreated(Boolean supergroupChatCreated) {
        this.supergroupChatCreated = supergroupChatCreated;
    }

    public Boolean getChannelChatCreated() {
        return channelChatCreated;
    }

    public void setChannelChatCreated(Boolean channelChatCreated) {
        this.channelChatCreated = channelChatCreated;
    }

    public Long getMigrateToChatId() {
        return migrateToChatId;
    }

    public void setMigrateToChatId(Long migrateToChatId) {
        this.migrateToChatId = migrateToChatId;
    }

    public Long getMigrateFromChatId() {
        return migrateFromChatId;
    }

    public void setMigrateFromChatId(Long migrateFromChatId) {
        this.migrateFromChatId = migrateFromChatId;
    }

    public Message getPinnedMessage() {
        return pinnedMessage;
    }

    public void setPinnedMessage(Message pinnedMessage) {
        this.pinnedMessage = pinnedMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (messageId != null ? !messageId.equals(message.messageId) : message.messageId != null) return false;
        if (from != null ? !from.equals(message.from) : message.from != null) return false;
        if (date != null ? !date.equals(message.date) : message.date != null) return false;
        if (chat != null ? !chat.equals(message.chat) : message.chat != null) return false;
        if (forwardFrom != null ? !forwardFrom.equals(message.forwardFrom) : message.forwardFrom != null) return false;
        if (forwardFromChat != null ? !forwardFromChat.equals(message.forwardFromChat) : message.forwardFromChat != null)
            return false;
        if (forwardDate != null ? !forwardDate.equals(message.forwardDate) : message.forwardDate != null) return false;
        if (replyToMessage != null ? !replyToMessage.equals(message.replyToMessage) : message.replyToMessage != null)
            return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(entities, message.entities)) return false;
        if (audio != null ? !audio.equals(message.audio) : message.audio != null) return false;
        if (document != null ? !document.equals(message.document) : message.document != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(photo, message.photo)) return false;
        if (sticker != null ? !sticker.equals(message.sticker) : message.sticker != null) return false;
        if (video != null ? !video.equals(message.video) : message.video != null) return false;
        if (voice != null ? !voice.equals(message.voice) : message.voice != null) return false;
        if (caption != null ? !caption.equals(message.caption) : message.caption != null) return false;
        if (contact != null ? !contact.equals(message.contact) : message.contact != null) return false;
        if (location != null ? !location.equals(message.location) : message.location != null) return false;
        if (venue != null ? !venue.equals(message.venue) : message.venue != null) return false;
        if (newChatMember != null ? !newChatMember.equals(message.newChatMember) : message.newChatMember != null)
            return false;
        if (leftChatMember != null ? !leftChatMember.equals(message.leftChatMember) : message.leftChatMember != null)
            return false;
        if (newChatTitle != null ? !newChatTitle.equals(message.newChatTitle) : message.newChatTitle != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(newChatPhoto, message.newChatPhoto)) return false;
        if (deleteChatPhoto != null ? !deleteChatPhoto.equals(message.deleteChatPhoto) : message.deleteChatPhoto != null)
            return false;
        if (groupChatCreated != null ? !groupChatCreated.equals(message.groupChatCreated) : message.groupChatCreated != null)
            return false;
        if (supergroupChatCreated != null ? !supergroupChatCreated.equals(message.supergroupChatCreated) : message.supergroupChatCreated != null)
            return false;
        if (channelChatCreated != null ? !channelChatCreated.equals(message.channelChatCreated) : message.channelChatCreated != null)
            return false;
        if (migrateToChatId != null ? !migrateToChatId.equals(message.migrateToChatId) : message.migrateToChatId != null)
            return false;
        if (migrateFromChatId != null ? !migrateFromChatId.equals(message.migrateFromChatId) : message.migrateFromChatId != null)
            return false;
        return pinnedMessage != null ? pinnedMessage.equals(message.pinnedMessage) : message.pinnedMessage == null;

    }

    @Override
    public int hashCode() {
        return messageId != null ? messageId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", from=" + from +
                ", date=" + date +
                ", chat=" + chat +
                ", forwardFrom=" + forwardFrom +
                ", forwardFromChat=" + forwardFromChat +
                ", forwardDate=" + forwardDate +
                ", replyToMessage=" + replyToMessage +
                ", text='" + text + '\'' +
                ", entities=" + Arrays.toString(entities) +
                ", audio=" + audio +
                ", document=" + document +
                ", photo=" + Arrays.toString(photo) +
                ", sticker=" + sticker +
                ", video=" + video +
                ", voice=" + voice +
                ", caption='" + caption + '\'' +
                ", contact=" + contact +
                ", location=" + location +
                ", venue=" + venue +
                ", newChatMember=" + newChatMember +
                ", leftChatMember=" + leftChatMember +
                ", newChatTitle='" + newChatTitle + '\'' +
                ", newChatPhoto=" + Arrays.toString(newChatPhoto) +
                ", deleteChatPhoto=" + deleteChatPhoto +
                ", groupChatCreated=" + groupChatCreated +
                ", supergroupChatCreated=" + supergroupChatCreated +
                ", channelChatCreated=" + channelChatCreated +
                ", migrateToChatId=" + migrateToChatId +
                ", migrateFromChatId=" + migrateFromChatId +
                ", pinnedMessage=" + pinnedMessage +
                '}';
    }*/
}
