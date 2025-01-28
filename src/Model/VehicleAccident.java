package src.Model;

import src.Model.Utils.SeverityNivel;

public class VehicleAccident extends Emergency{

    public VehicleAccident(String location, SeverityNivel severity, int responseTime) {
        super("Accidente vehicular", location, severity, responseTime);
    }

}
