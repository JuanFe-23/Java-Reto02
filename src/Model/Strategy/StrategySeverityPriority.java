package src.Model.Strategy;

import src.Model.Emergency;
import src.Model.Interfaces.IPriority;

public class StrategySeverityPriority implements IPriority {

    @Override
    public int calculatePriority(Emergency emergency) {

        switch (emergency.getSeverity()) {
            case LOW:
                return 1;
            case MEDIUM:
                return 2;
            case HIGH:
                return 3;
            default:
                return 1;
        }
    }

}
