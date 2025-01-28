package src.Model;

import src.SeverityNivel;

public class VehicleAccident extends Emergency{

    public VehicleAccident(String location, SeverityNivel severity, int responseTime) {
        super("Accidente vehicular", location, severity, responseTime);
    }

}
