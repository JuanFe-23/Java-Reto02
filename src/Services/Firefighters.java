package src.Services;

import src.Model.Emergency;

public class Firefighters extends BaseService{

    public Firefighters(int staffAvailable, int fuel) {
        super("Bomberos", staffAvailable, fuel);
      
    }

    @Override
    public void attendedEmergency(Emergency emergency) {
        System.out.println("Bomberos en camino!!!");
        System.out.println("-> [Bomberos" + getName() + "]: " + emergency.toString());

        assingStaff(5);
        spendFuel(10);
    }

}
