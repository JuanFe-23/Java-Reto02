package src.View;

import java.util.List;
import java.util.Scanner;

import src.Controller.EmergencySystem;
import src.Model.Emergency;
import src.Model.UrbanMap;
import src.Model.Factory.FactoryEmergency;
import src.Model.Services.Firefighters;
import src.Model.Services.Paramedics;
import src.Model.Services.Police;
import src.Model.Utils.EmergencyType;
import src.Model.Utils.SeverityNivel;

public class Main {
    public static void main(String[] args) throws Exception {

        EmergencySystem system = EmergencySystem.getInstance();
        UrbanMap urbanMap = new UrbanMap();

        initializeResources(system);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------ MENÚ PRINCIPAL ------");
            System.out.println("\n ------ SISTEMA DE GESTIÓN DE EMERGENCIAS ------");
            System.out.println("1. Registrar Emergencia");
            System.out.println("2. Ver recursos disponibles");
            System.out.println("3. Atender una emergencia");
            System.out.println("4. Ver estadísticas del dia");
            System.out.println("5. Finalizar jornada (Cerrar sistema)");

            System.out.print("Seleccione una opción: ");

            int option = 0;
            try {
                option = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Opcion no valida");
            }

            switch (option) {
                case 1:
                    emergencyRegisterMenu(system, sc, urbanMap);
                    break;
                case 2:
                    system.showResources(sc);
                    break;
                case 3:
                    attendEmergencyMenu(system, sc);
                    break;
                case 4:
                    system.showStatistics();
                    break;
                case 5:
                    System.out.println("Finalizando jornada...");
                    system.endDay();
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        }

        sc.close();

    }

    private static void initializeResources(EmergencySystem system) {
        system.addResource(new Firefighters(" Carro - U1", 5, 100));
        system.addResource(new Firefighters(" Carro - U2", 4, 80));
        system.addResource(new Paramedics(" Ambulancia - A1", 3, 85));
        system.addResource(new Paramedics(" Ambulancia - A2", 2, 70));
        system.addResource(new Police(" Patrulla - M1", 2, 50));
        system.addResource(new Police(" Patrulla - C1", 4, 100));
    }

    private static void emergencyRegisterMenu(EmergencySystem system, Scanner sc, UrbanMap urbanMap) {
        System.out.println("\n------ REGISTRAR EMERGENCIA ------");
        System.out.println("1. Incendio");
        System.out.println("2. Robo");
        System.out.println("3. Accidente de vehiculo");
        System.out.print("Seleccione el tipo de emergencia: ");
        int option = sc.nextInt();
        sc.nextLine();

        EmergencyType type = null;
        switch (option) {
            case 1 -> type = EmergencyType.FIRE;
            case 2 -> type = EmergencyType.HEIST;
            case 3 -> type = EmergencyType.VEHICLE_ACCIDENT;
            default -> {
                System.out.println("Opcion no valida");
                return;
            }

        }

        System.out.print(
                "Ingrese la ubicación de la emergencia (zona-norte, zona-sur, zona-centro, zona-oriente, zona-occidente):  ");
        String location = sc.nextLine().toLowerCase();
        while (!urbanMap.isValidLocation(location)) {
            System.out.println("Ubicación no valida, intente nuevamente.");
            System.out.print(
                    "Ingrese la ubicación de la emergencia (zona-norte, zona-sur, zona-centro, zona-oriente, zona-occidente):  ");
            location = sc.nextLine().toLowerCase();

        }
        System.out.print("Ingrese el nivel de gravedad de la emergencia (1. Bajo, 2. Medio, 3. Alto): ");
        SeverityNivel severityNivel = null;
        switch (Integer.parseInt(sc.nextLine())) {
            case 1 -> severityNivel = SeverityNivel.BAJO;
            case 2 -> severityNivel = SeverityNivel.MEDIO;
            case 3 -> severityNivel = SeverityNivel.ALTO;

            default -> {
                System.out.println("Nivel de gravedad no valido");
                return;
            }
        }

        System.out.print("Ingrese el tiempo estimado de atencion de la emergencia (en minutos): ");
        int estimatedTime = Integer.parseInt(sc.nextLine());

        Emergency newE = FactoryEmergency.createEmergency(type, location, severityNivel, estimatedTime, urbanMap);
        if (newE == null) {
            System.out.println("Tipo de emergencia inválido");
            return;
        }

        system.registerNewEmergency(newE);
        System.out.println("Emergencia registrada: " + newE);

    }

    private static void attendEmergencyMenu(EmergencySystem system, Scanner sc) {

        List<Emergency> pendings = system.getEmergenciesPend();
        if (pendings.isEmpty()) {
            System.out.println("No hay emergencias pendientes por antender.");
            return;
        }

        System.out.println("\n------ ATENDER EMERGENCIA ------");
        for (int i = 0; i < pendings.size(); i++) {
            System.out.println((i + 1) + ". " + pendings.get(i).toString());
        }

        System.out.print("Seleccione la emergencia a atender: ");
        int index = sc.nextInt() - 1;
        if (index < 0 || index >= pendings.size()) {
            System.out.println("Opcion no valida");
            return;
        }

        Emergency emergency = pendings.get(index);
        system.allocateResources(emergency);
        system.attendEmergency(emergency);

    }

}
