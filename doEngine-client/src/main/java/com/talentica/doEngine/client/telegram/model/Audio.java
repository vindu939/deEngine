package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 8/5/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audio {

    @JsonProperty(value = "file_id")
    private String fileId;

    private Integer duration;

    private String performer;

    private String title;

    @JsonProperty(value = "mimeType")
    private String mimeType;

    @JsonProperty(value = "fileSize")
    private Integer fileSize;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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

        Audio audio = (Audio) o;

        if (fileId != null ? !fileId.equals(audio.fileId) : audio.fileId != null) return false;
        if (duration != null ? !duration.equals(audio.duration) : audio.duration != null) return false;
        if (performer != null ? !performer.equals(audio.performer) : audio.performer != null) return false;
        if (title != null ? !title.equals(audio.title) : audio.title != null) return false;
        if (mimeType != null ? !mimeType.equals(audio.mimeType) : audio.mimeType != null) return false;
        return fileSize != null ? fileSize.equals(audio.fileSize) : audio.fileSize == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "Audio{" +
                "file_id='" + fileId + '\'' +
                ", duration=" + duration +
                ", performer='" + performer + '\'' +
                ", title='" + title + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }*/
}
