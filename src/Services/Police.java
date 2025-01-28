package src.Services;

import src.Model.Emergency;

public class Police extends BaseService{

    public Police(int staffAvailable, int fuel) {
        super("Oficiales", staffAvailable, fuel);
       }

    @Override
    public void attendedEmergency(Emergency emergency) {

        System.out.println("Policia en camino!!!");
        System.out.println("-> [policia" + getName() + "]: " + emergency.toString());

        assingStaff(2);
        spendFuel(3);
     
    }




}


