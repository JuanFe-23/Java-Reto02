package Model.Services;

import Model.Emergency;

public class Firefighters extends BaseService{

    public Firefighters(String id, int staffAvailable, int fuel) {
        super(id, staffAvailable, fuel);
      
    }

    @Override
    public void attendedEmergency(Emergency emergency) {
        System.out.println("Bomberos en camino!!!");
        System.out.println("-> [Bomberos" + getName() + "]: " + emergency.toString());

        assingStaff(5);
        spendFuel(15);
    }

}
