package src.Model.Interfaces;

import src.Model.Emergency;

public interface IEmergencyService {

    String getName();

    int getSttaffAvailable();

    int getFuel();

    boolean isAvailable();

    void assingStaff(int amount);

    void releaseStaff(int amount);

    void spendFuel(int amount);

    void putFuel(int amount);

    void attendedEmergency(Emergency emergency);

    



}
