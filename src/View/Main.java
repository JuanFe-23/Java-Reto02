package src.View;

import java.util.Scanner;

import src.Controller.EmergencySystem;
import src.Model.Services.Firefighters;
import src.Model.Services.Paramedics;
import src.Model.Services.Police;

public class Main {
    public static void main(String[] args) throws Exception {

        EmergencySystem system = EmergencySystem.getInstance();

        initializeResources(system);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------ MENÚ PRINCIPAL ------");
            System.out.println("\n ------ SISTEMA DE GESTIÓN DE EMERGENCIAS ------");
            System.out.println(" 1. Registrar Emergencia");
            System.out.println("2. Ver recursos disponibles");
            System.out.println("3. Atender una emergencia");
            System.out.println("4. Ver estadísticas del dia");
            System.out.println("5. Finalizar jornada (Cerrar sistema)");

            System.out.print("Seleccione una opció: ");
            
            int option = 0;
            try {
                option = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Opcion no valida");
            }

            switch (option) {
                case 1:
                // Registrar Emergencia Menu
                break;
                case 2:
                // Mostrar recursos disponibles Menu              
                break;
                case 3:
                // Atender emergencia Menu
                break;
                case 4:
                // Ver estadisticas del dia Menu
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
        system.addResource(new Firefighters("Bomberos - U1",5, 100));
        system.addResource(new Firefighters("Bomberos - U2",4, 80));
        system.addResource(new Paramedics("Ambulancia - A1",3, 85));
        system.addResource(new Paramedics("Ambulancia - A2",2, 70));
        system.addResource(new Police("Oficiales - M1",2, 50));
        system.addResource(new Police("Oficiales - C2",4, 100));
    }
}
