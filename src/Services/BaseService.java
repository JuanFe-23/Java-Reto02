package src.Services;

import src.Model.Emergency;
import src.Model.Interfaces.IEmergencyService;

public abstract class BaseService implements IEmergencyService {

    private String name;
    private int staffAvailable;
    private int fuel;

    public BaseService(String name, int staffAvailable, int fuel) {
        this.name = name;
        this.staffAvailable = staffAvailable;
        this.fuel = fuel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSttaffAvailable() {
        return staffAvailable;
    }

    @Override
    public int getFuel() {
        return fuel;
    }

    @Override
    public boolean isAvailable() {
        return staffAvailable > 0 && fuel > 0;
    }

    @Override
    public void assingStaff(int amount) {
        if (amount <= staffAvailable) {
            staffAvailable -= amount;
            
        } else{
            System.out.println("Por el momento hay suficiente personal para atender la emergencia");
        }
    }

    @Override
    public void releaseStaff(int amount) {
        staffAvailable += amount;
    }

    @Override
    public void spendFuel(int amount) {
        fuel -= amount;
    }

    @Override
    public void putFuel(int amount) {
        fuel += amount;
    }

    public abstract void attendedEmergency(Emergency emergency);

    @Override
    public String toString() {
        return "BaseService{" +
                "Servicio='" + name + '\'' +
                ", Personal Disponible=" + staffAvailable +
                ", Combustible=" + fuel +
                '}';
    }





}
