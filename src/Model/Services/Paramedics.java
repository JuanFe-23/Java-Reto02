package src.Model.Services;

import src.Model.Emergency;

public class Paramedics extends BaseService {

    public Paramedics(String id,int staffAvailable, int fuel) {
        super(id, staffAvailable, fuel);
    }

    @Override
    public void attendedEmergency(Emergency emergency) {

        System.out.println("Ambulancia en camino a la emergencia");
        System.out.println("-> [Ambulancia" + getName() + "] " + emergency.toString());

        assingStaff(3);
        spendFuel(5);

    }

    

}
