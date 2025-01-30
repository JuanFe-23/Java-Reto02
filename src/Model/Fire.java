package src.Model;

import src.Model.Utils.SeverityNivel;

public class Fire extends Emergency {

    public Fire(String location, SeverityNivel severity, int responseTime, int distance) {
        super("Incendio", location, severity, responseTime);
    }

}
