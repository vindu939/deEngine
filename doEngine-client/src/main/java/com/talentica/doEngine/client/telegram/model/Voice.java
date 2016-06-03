package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 10/21/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Voice {

    @JsonProperty(value = "file_id")
    private String fileId;

    private Integer duration;

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

        Voice voice = (Voice) o;

        if (fileId != null ? !fileId.equals(voice.fileId) : voice.fileId != null) return false;
        if (duration != null ? !duration.equals(voice.duration) : voice.duration != null) return false;
        if (mimeType != null ? !mimeType.equals(voice.mimeType) : voice.mimeType != null) return false;
        return fileSize != null ? fileSize.equals(voice.fileSize) : voice.fileSize == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "Voice{" +
                "fileId='" + fileId + '\'' +
                ", duration=" + duration +
                ", mimeType='" + mimeType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }*/
}