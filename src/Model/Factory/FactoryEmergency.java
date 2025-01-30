package src.Model.Factory;

import src.Model.Emergency;
import src.Model.Fire;
import src.Model.Heist;
import src.Model.UrbanMap;
import src.Model.VehicleAccident;
import src.Model.Utils.EmergencyType;
import src.Model.Utils.SeverityNivel;

public class FactoryEmergency {

    public static Emergency createEmergency(EmergencyType type, String location, SeverityNivel severity, int responseTime, UrbanMap urbanMap) {

        int distance = urbanMap.calculateDistance(location);

        return switch (type) {
            case FIRE -> new Fire(location, severity, responseTime, distance);
            case HEIST -> new Heist(location, severity, responseTime, distance);
            case VEHICLE_ACCIDENT -> new VehicleAccident(location, severity, responseTime, distance);
            default -> null;
           
        };
    }

}
