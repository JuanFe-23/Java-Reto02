package Model;

import Model.Utils.SeverityNivel;

public class Heist extends Emergency{

    public Heist(String location, SeverityNivel severity, int responseTime, int distance) {
        super("Robo", location, severity, responseTime);
    }

}
