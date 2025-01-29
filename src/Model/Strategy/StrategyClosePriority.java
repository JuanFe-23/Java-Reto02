package src.Model.Strategy;

import src.Model.Emergency;
import src.Model.UrbanMap;
import src.Model.Interfaces.IPriority;

public class StrategyClosePriority implements IPriority {

    private UrbanMap urbanMap;

    public StrategyClosePriority() {
        this.urbanMap = new UrbanMap();
    }

    @Override
    public int calculatePriority(Emergency emergency) {

        int distance = urbanMap.calculateDistance(emergency.getLocation());

        int priority = 10 - distance;

        System.out.println("Emergencia en " + emergency.getLocation() + " tiene una distancia de " + distance + " km.");

        return priority;
    }

}
