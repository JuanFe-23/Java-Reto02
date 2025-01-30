package src.Model.Services;

import src.Model.Emergency;

public class Police extends BaseService{

    public Police(String id,int staffAvailable, int fuel) {
        super(id, staffAvailable, fuel);
       }

    @Override
    public void attendedEmergency(Emergency emergency) {

        System.out.println("Policia en camino!!!");
        System.out.println("-> [policia" + getName() + "]: " + emergency.toString());

        assingStaff(2);
        spendFuel(3);
     
    }




}


