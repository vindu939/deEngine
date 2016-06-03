package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 8/5/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sticker {

    @JsonProperty(value = "file_id")
    private String fileId;

    private Integer width;

    private Integer height;

    private PhotoSize thumb;

    private String emoji;

    @JsonProperty(value = "file_size")
    private Integer fileSize;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public PhotoSize getThumb() {
        return thumb;
    }

    public void setThumb(PhotoSize thumb) {
        this.thumb = thumb;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sticker sticker = (Sticker) o;

        if (fileId != null ? !fileId.equals(sticker.fileId) : sticker.fileId != null) return false;
        if (width != null ? !width.equals(sticker.width) : sticker.width != null) return false;
        if (height != null ? !height.equals(sticker.height) : sticker.height != null) return false;
        if (thumb != null ? !thumb.equals(sticker.thumb) : sticker.thumb != null) return false;
        if (emoji != null ? !emoji.equals(sticker.emoji) : sticker.emoji != null) return false;
        return fileSize != null ? fileSize.equals(sticker.fileSize) : sticker.fileSize == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "Sticker{" +
                "fileId='" + fileId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", thumb=" + thumb +
                ", emoji='" + emoji + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }*/
}
