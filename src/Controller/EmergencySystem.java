package src.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import src.Model.Emergency;
import src.Model.Fire;
import src.Model.Heist;
import src.Model.VehicleAccident;
import src.Model.Interfaces.IEmergencyService;
import src.Model.Interfaces.IPriority;
import src.Model.Observer.EmergencySubject;
import src.Model.Observer.ObserverEmergencies;
import src.Model.Services.Firefighters;
import src.Model.Services.Police;
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

    public void addResource(IEmergencyService resource) {
        resources.add(resource);
    }

    public void showResources() {
        System.out.println("\n------ ESTADO DE RECURSOS ------");
        for (IEmergencyService resource : resources) {
            System.out.println(resource.toString());
        }
    }

    public List<IEmergencyService> filterAviableResources() {
        return resources.stream().filter(resource -> resource.isAvailable()).collect(Collectors.toList());
    }

    public void registerNewEmergency(Emergency e) {
        listEmergencies.add(e);
        notifyEmergencies(e);
    }

    public List<Emergency> getEmergenciesPend() {
        return listEmergencies.stream().filter(e -> !e.attended()).collect(Collectors.toList());
    }

    public void allocateResources(Emergency emergency) {
        List<IEmergencyService> aviableResources = filterAviableResources();
        if (aviableResources.isEmpty()) {
            System.out.println("No hay recursos disponibles para atender la emergencia");
            return;
        }
        System.out.println("-> Asignando los recursos atomaticamente");

        if (emergency instanceof Fire) {
            for (IEmergencyService resource : aviableResources) {
                if (resource instanceof Firefighters) {
                    resource.attendedEmergency(emergency);
                    break;
                }
            }

        } else if (emergency instanceof Heist) {
            for (IEmergencyService resource : aviableResources) {
                if (resource instanceof Police) {
                    resource.attendedEmergency(emergency);
                    break;
                }
            }
        } else if (emergency instanceof VehicleAccident) {
            for (IEmergencyService resource : aviableResources) {
                if (resource instanceof Firefighters) {
                    resource.attendedEmergency(emergency);
                    break;
                }
            }

        }
    }

    public void attendEmergency(Emergency emergency) {
        if (emergency.attended()) {
            System.out.println("La emergencia ya fue atendida");
            return;
        }

        emergency.startAttention();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        emergency.endAttention();
        System.out.println("Emergencia atendida: " + emergency.toString());

        emergeciesAttended++;
        totalAttentionTime += emergency.getResponseTime();
    }

    public void showStatistics() {
        System.out.println("\n------ ESTADÍSTICAS DEL DÍA ------");
        System.out.println("Emergencias atendidas: " + emergeciesAttended);

        long averageMs = 0;
        if (emergeciesAttended > 0) {
            averageMs = totalAttentionTime / emergeciesAttended;
        }

        double averageS = averageMs / 1000.0;
        System.out.println("Tiempo promedio de atencion: " + averageS + " segundos");

        long noAttended = listEmergencies.stream().filter(e -> !e.attended()).count();
        System.out.println("Emergencias sin atender: " + noAttended);

    }

    public void endDay() {
        showStatistics();
        System.out.println("Guardando estadísticas del día...");
        System.out.println("\n------ FIN DEL DÍA ------");
        
    }

    public void setStrategyPriority(IPriority newPriority) {
        this.priority = newPriority;
    }

}
