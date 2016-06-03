package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 8/5/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty(value = "file_id")
    private String fileId;

    private Integer width;

    private Integer height;

    private Integer duration;

    private PhotoSize thumb;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public PhotoSize getThumb() {
        return thumb;
    }

    public void setThumb(PhotoSize thumb) {
        this.thumb = thumb;
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

        Video video = (Video) o;

        if (fileId != null ? !fileId.equals(video.fileId) : video.fileId != null) return false;
        if (width != null ? !width.equals(video.width) : video.width != null) return false;
        if (height != null ? !height.equals(video.height) : video.height != null) return false;
        if (duration != null ? !duration.equals(video.duration) : video.duration != null) return false;
        if (thumb != null ? !thumb.equals(video.thumb) : video.thumb != null) return false;
        if (mimeType != null ? !mimeType.equals(video.mimeType) : video.mimeType != null) return false;
        return fileSize != null ? fileSize.equals(video.fileSize) : video.fileSize == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "Video{" +
                "fileId='" + fileId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", duration=" + duration +
                ", thumb=" + thumb +
                ", mimeType='" + mimeType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }*/
}
