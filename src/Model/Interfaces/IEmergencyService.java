package src.Model.Interfaces;

import src.Model.Emergency;

public interface IEmergencyService {

    String getName();

    int getSttaffAvailable();

    boolean getFuel();

    void assingStaff();

    void releaseStaff();

    void spendFuel();

    void putFuel();

    void attendedEmergency(Emergency emergency);

    



}
