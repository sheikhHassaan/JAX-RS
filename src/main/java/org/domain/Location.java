package org.domain;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Location {
    @SerializedName("location_id")
    private UUID locationId;
    @SerializedName("location_name")
    private String locationName;



    public Location(){}

    public Location(UUID locId, String locName){
        this.locationId = locId;
        this.locationName = locName;
    }

    public Location(String locName){
        this.locationName = locName;
    }

    public Location(Location location){
        this.locationId = location.getLocationId();
        this.locationName = location.getLocationName();
    }

    public boolean equals(Location location){
        return this.locationName == location.getLocationName();
    }

    public UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                '}';
    }

}
