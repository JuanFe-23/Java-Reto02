package src.Model;

import src.SeverityNivel;

public class Fire extends Emergency {

    public Fire(String location, SeverityNivel severity, int responseTime) {
        super("Incendio", location, severity, responseTime);
    }

}
