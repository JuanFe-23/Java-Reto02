package src.Services;

import src.Model.Emergency;

public class Paramedics extends BaseService {

    public Paramedics(int staffAvailable, int fuel) {
        super("Paramedicos", staffAvailable, fuel);
    }

    @Override
    public void attendedEmergency(Emergency emergency) {

        System.out.println("Ambulancia en camino a la emergencia");
        System.out.println("-> [Ambulancia" + getName() + "] " + emergency.toString());

        assingStaff(3);
        spendFuel(5);

    }

    

}
