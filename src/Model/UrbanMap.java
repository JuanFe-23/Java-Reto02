package src.Model;

import java.util.ArrayList;
import java.util.List;

public class UrbanMap {

    private List<Location> locations;

    public UrbanMap() {
        locations = new ArrayList<>();

        locations.add(new Location("Zona-norte", 8));
        locations.add(new Location("Zona-sur", 10));
        locations.add(new Location("Zona-centro", 2));
        locations.add(new Location("Zona-oriente", 5));
        locations.add(new Location("Zona-occidente", 6));
    }

    public int calculateDistance(String locationName) {
        for (Location location : locations) {
            if (location.getZone().equalsIgnoreCase(locationName)) {
                return location.getDistance();

            }

        }

        return 10;
    }

}
