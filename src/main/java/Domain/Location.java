package Domain;

import java.util.UUID;

public class Location {
    private UUID location_id;
    private String location_name;



    public Location(){}

    public Location(Location location){
        this.location_id = location.getLocation_id();
        this.location_name = location.getLocation_name();
    }

    public boolean equals(Location location){
//        if (this.location_id == location.getLocation_id())
            return this.location_name == location.getLocation_name();
//        return false;
    }

    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "location_id=" + location_id +
                ", location_name='" + location_name + '\'' +
                '}';
    }

}
