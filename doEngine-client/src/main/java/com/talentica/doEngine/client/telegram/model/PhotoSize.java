package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 8/4/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoSize {

    @JsonProperty(value = "file_id")
    private String fileId;

    private Integer width;

    private Integer height;

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

        PhotoSize photoSize = (PhotoSize) o;

        if (fileId != null ? !fileId.equals(photoSize.fileId) : photoSize.fileId != null) return false;
        if (width != null ? !width.equals(photoSize.width) : photoSize.width != null) return false;
        if (height != null ? !height.equals(photoSize.height) : photoSize.height != null) return false;
        return fileSize != null ? fileSize.equals(photoSize.fileSize) : photoSize.fileSize == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "PhotoSize{" +
                "fileId='" + fileId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", fileSize=" + fileSize +
                '}';
    }*/
}
