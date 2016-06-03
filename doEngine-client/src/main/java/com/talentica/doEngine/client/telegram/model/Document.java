package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 8/5/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {

    @JsonProperty(value = "file_id")
    private String fileId;

    private PhotoSize thumb;

    private String file_name;

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

    public PhotoSize getThumb() {
        return thumb;
    }

    public void setThumb(PhotoSize thumb) {
        this.thumb = thumb;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
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

        Document document = (Document) o;

        if (fileId != null ? !fileId.equals(document.fileId) : document.fileId != null) return false;
        if (thumb != null ? !thumb.equals(document.thumb) : document.thumb != null) return false;
        if (file_name != null ? !file_name.equals(document.file_name) : document.file_name != null) return false;
        if (mimeType != null ? !mimeType.equals(document.mimeType) : document.mimeType != null) return false;
        return fileSize != null ? fileSize.equals(document.fileSize) : document.fileSize == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "Document{" +
                "fileId='" + fileId + '\'' +
                ", thumb=" + thumb +
                ", file_name='" + file_name + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }*/
}
