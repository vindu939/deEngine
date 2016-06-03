package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stas Parshin
 * 16 October 2015
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class File {

    @JsonProperty(value = "file_id")
    private String fileId;

    @JsonProperty(value = "file_size")
    private Integer fileSize;

    @JsonProperty(value = "file_path")
    private String filePath;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (fileId != null ? !fileId.equals(file.fileId) : file.fileId != null) return false;
        if (fileSize != null ? !fileSize.equals(file.fileSize) : file.fileSize != null) return false;
        return filePath != null ? filePath.equals(file.filePath) : file.filePath == null;
    }

    @Override
    public int hashCode() {
        return fileId != null ? fileId.hashCode() : 0;
    }

    /*@Override
    public String toString() {
        return "File{" +
                "fileId='" + fileId + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }*/
}
