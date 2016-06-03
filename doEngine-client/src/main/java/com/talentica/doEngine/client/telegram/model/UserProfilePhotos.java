package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * stas
 * 8/5/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfilePhotos {

    @JsonProperty(value = "total_count")
    private Integer totalCount;

    private PhotoSize[][] photos;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public PhotoSize[][] getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoSize[][] photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfilePhotos that = (UserProfilePhotos) o;

        if (totalCount != null ? !totalCount.equals(that.totalCount) : that.totalCount != null) return false;
        return Arrays.deepEquals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        int result = totalCount != null ? totalCount.hashCode() : 0;
        result = 31 * result + Arrays.deepHashCode(photos);
        return result;
    }

    /*@Override
    public String toString() {
        return "UserProfilePhotos{" +
                "totalCount=" + totalCount +
                ", photos=" + Arrays.toString(photos) +
                '}';
    }*/
}
