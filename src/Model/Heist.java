package src.Model;

import src.SeverityNivel;

public class Heist extends Emergency{

    public Heist(String location, SeverityNivel severity, int responseTime) {
        super("Robo", location, severity, responseTime);
    }

}
