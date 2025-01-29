package src.Controller;

import java.util.ArrayList;
import java.util.List;

import src.Model.Emergency;
import src.Model.Interfaces.IEmergencyService;
import src.Model.Interfaces.IPriority;
import src.Model.Observer.EmergencySubject;
import src.Model.Observer.ObserverEmergencies;
import src.Model.Strategy.StrategySeverityPriority;

public class EmergencySystem implements EmergencySubject {

    private static EmergencySystem instance;
    private List<Emergency> listEmergencies;
    private List<IEmergencyService> resources;
    private List<ObserverEmergencies> observers;

    private IPriority priority;

    private long totalAttentionTime;
    private int emergeciesAttended;

    private EmergencySystem() {
        priority = new StrategySeverityPriority();
        listEmergencies = new ArrayList<>();
        resources = new ArrayList<>();
        observers = new ArrayList<>();
        emergeciesAttended = 0;
        totalAttentionTime = 0;
    }

    public static EmergencySystem getInstance() {
        if (instance == null) {
            instance = new EmergencySystem();
        }
        return instance;
    }

    @Override
    public void addObserver(ObserverEmergencies observerEmergencies) {
        observers.add(observerEmergencies);
    }

    @Override
    public void removeObserver(ObserverEmergencies observerEmergencies) {
        observers.remove(observerEmergencies);
    }

    @Override
    public void notifyEmergencies(Emergency emergency) {
        for (ObserverEmergencies observerEmergencies : observers) {
            observerEmergencies.newEmergency(emergency);

        }

    }

    



}
