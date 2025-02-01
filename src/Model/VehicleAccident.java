package Model;

import Model.Utils.SeverityNivel;

public class VehicleAccident extends Emergency{

    public VehicleAccident(String location, SeverityNivel severity, int responseTime, int distance) {
        super("Accidente vehicular", location, severity, responseTime);
    }

}
