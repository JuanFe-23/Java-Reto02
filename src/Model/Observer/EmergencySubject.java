package src.Model.Observer;

import src.Model.Emergency;

public interface EmergencySubject {

    void addObserver(ObserverEmergencies observerEmergencies);

    void removeObserver(ObserverEmergencies observerEmergencies);

    void notifyEmergencies(Emergency emergency);

}
