package Model.Strategy;

import Model.Emergency;
import Model.Interfaces.IPriority;

public class StrategySeverityPriority implements IPriority {

    @Override
    public int calculatePriority(Emergency emergency) {

        switch (emergency.getSeverity()) {
            case BAJO:
                return 1;
            case MEDIO:
                return 2;
            case ALTO:
                return 3;
            default:
                return 1;
        }
    }

}
