package src.Model.Factory;

import src.Model.Emergency;
import src.Model.Fire;
import src.Model.Heist;
import src.Model.VehicleAccident;
import src.Model.Utils.EmergencyType;
import src.Model.Utils.SeverityNivel;

public class FactoryEmergency {

    public static Emergency createEmergency(EmergencyType type, String location, SeverityNivel severity, int responseTime) {

        switch (type) {
            case FIRE:
                return new Fire(location, severity, responseTime);
            case HEIST:
                return new Heist(location, severity, responseTime);
            case VEHICLE_ACCIDENT:
                return new VehicleAccident(location, severity, responseTime);
            default:
                return null;
        }
    }

}
