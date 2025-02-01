package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Model.Emergency;
import Model.Fire;
import Model.Heist;
import Model.UrbanMap;
import Model.VehicleAccident;
import Model.Interfaces.IEmergencyService;
import Model.Interfaces.IPriority;
import Model.Observer.EmergencySubject;
import Model.Observer.ObserverEmergencies;
import Model.Services.Firefighters;
import Model.Services.Paramedics;
import Model.Services.Police;
import Model.Strategy.StrategySeverityPriority;

public class EmergencySystem implements EmergencySubject {

    private static EmergencySystem instance;
    private List<Emergency> listEmergencies;
    private List<IEmergencyService> resources;
    private List<ObserverEmergencies> observers;
    private IPriority priority;
    private UrbanMap urbanMap = new UrbanMap();

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

    public void showResources(Scanner sc) {
        System.out.println("\n------ ESTADO DE RECURSOS ------");
        for (IEmergencyService resource : resources) {
            System.out.println(resource.toString());
        }

        System.out.print("\n1. ¿Desea agregar más personal? (S/N): ");
        String option = sc.next().toLowerCase();
        sc.nextLine();
        if (option.equals("s")) {
            System.out.print("Ingrese el nombre del servicio de emergencia: ");
            String recourseName = sc.nextLine().toLowerCase();

            IEmergencyService selectedResource = resources.stream()
                    .filter(r -> r.getName().toLowerCase().contains(recourseName.toLowerCase()))
                    .findFirst().orElse(null);

            if (selectedResource != null) {
                System.out.print("Ingrese la cantidad de personal a agregar: ");
                int amount = sc.nextInt();
                sc.nextLine();
                selectedResource.releaseStaff(amount);
                System.out.println("Personal agregado a: " + recourseName);

            } else {
                System.out.println("Servicio de emergencia no encontrado.");
            }

        }

        System.out.print("2. ¿Desea agregar combustible? (S/N): ");
        option = sc.next().toLowerCase();
        sc.nextLine();
        if (option.equals("s")) {
            System.out.print("Ingrese el nombre del servicio de emergencia: ");
            String recourseName = sc.nextLine().toLowerCase();
            IEmergencyService selectedResource = resources.stream()
                    .filter(r -> r.getName().toLowerCase().contains(recourseName.toLowerCase()))
                    .findFirst().orElse(null);

            if (selectedResource != null) {
                System.out.print("Ingrese la cantidad de combustible a agregar (litros): ");
                int amount = sc.nextInt();
                sc.nextLine();
                selectedResource.putFuel(amount);
                System.out.println("Combustible agregado a: " + recourseName);
            } else {
                System.out.println("Servicio de emergencia no encontrado.");
            }
        }
    }

    public List<IEmergencyService> filterAviableResources() {
        return resources.stream().filter(resource -> resource.isAvailable()).collect(Collectors.toList());
    }

    public void registerNewEmergency(Emergency e) {
        int distance = urbanMap.calculateDistance(e.getLocation());
        e.setResponseTime(e.getResponseTime() + distance * 2);
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

        boolean resourceAssigned = false;

        if (emergency instanceof Fire){
            for (IEmergencyService resource : aviableResources) {
                if (resource instanceof Firefighters) {
                    resource.attendedEmergency(emergency);
                    resourceAssigned = true;
                    break;
                }
            }
        } else if (emergency instanceof Heist){
            for (IEmergencyService resource : aviableResources) {
                if (resource instanceof Police) {
                    resource.attendedEmergency(emergency);
                    resourceAssigned = true;
                    break;
                }
            }
        } else if (emergency instanceof VehicleAccident){
            for (IEmergencyService resource : aviableResources) {
                if (resource instanceof Paramedics) {
                    resource.attendedEmergency(emergency);
                    resourceAssigned = true;
                    break;
                }
            }
        }

        if (!resourceAssigned) {
            System.out.println("No hay recursos disponibles para atender la emergencia");
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
        System.out.println("\nEmergencia atendida con exito... \nEmergencia: " + emergency.toString());

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
        System.out.println("\n------ FIN DEL DÍA ------\n");

    }

    public void setStrategyPriority(IPriority newPriority) {
        this.priority = newPriority;
    }

}
