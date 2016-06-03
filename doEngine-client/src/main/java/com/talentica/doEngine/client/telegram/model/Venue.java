package com.talentica.doEngine.client.telegram.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * stas
 * 5/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Venue {

    private Location location;

    private String title;

    private String address;

    @JsonProperty(value = "foursquare_id")
    private String foursquareId;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFoursquareId() {
        return foursquareId;
    }

    public void setFoursquareId(String foursquareId) {
        this.foursquareId = foursquareId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        if (location != null ? !location.equals(venue.location) : venue.location != null) return false;
        if (title != null ? !title.equals(venue.title) : venue.title != null) return false;
        if (address != null ? !address.equals(venue.address) : venue.address != null) return false;
        return foursquareId != null ? foursquareId.equals(venue.foursquareId) : venue.foursquareId == null;

    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (foursquareId != null ? foursquareId.hashCode() : 0);
        return result;
    }

    /*@Override
    public String toString() {
        return "Venue{" +
                "location=" + location +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", foursquareId='" + foursquareId + '\'' +
                '}';
    }*/
}
