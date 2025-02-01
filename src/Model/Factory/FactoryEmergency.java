package Model.Factory;

import Model.Emergency;
import Model.Fire;
import Model.Heist;
import Model.UrbanMap;
import Model.VehicleAccident;
import Model.Utils.EmergencyType;
import Model.Utils.SeverityNivel;

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
