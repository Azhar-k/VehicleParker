package com.azhar.VehicleParker.Entities.Building;

import com.azhar.VehicleParker.Entities.Vehicle.*;

public class LevelCapacity {
    private  Vehicle carCapacity;
    private  Vehicle busCapacity;
    private  Vehicle bikeCapacity;
    private  Vehicle vanCapacity;

    public LevelCapacity(Vehicle carCapacity, Vehicle busCapacity, Vehicle bikeCapacity, Vehicle vanCapacity) {
        this.carCapacity = carCapacity;
        this.busCapacity = busCapacity;
        this.bikeCapacity = bikeCapacity;
        this.vanCapacity = vanCapacity;
    }

    public Vehicle getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(Vehicle carCapacity) {
        this.carCapacity = carCapacity;
    }

    public Vehicle getBusCapacity() {
        return busCapacity;
    }

    public void setBusCapacity(Vehicle busCapacity) {
        this.busCapacity = busCapacity;
    }

    public Vehicle getBikeCapacity() {
        return bikeCapacity;
    }

    public void setBikeCapacity(Vehicle bikeCapacity) {
        this.bikeCapacity = bikeCapacity;
    }

    public Vehicle getVanCapacity() {
        return vanCapacity;
    }

    public void setVanCapacity(Vehicle vanCapacity) {
        this.vanCapacity = vanCapacity;
    }

    public int getFreeSlots(Vehicle vehicle){
        int freeSlots=0;
        if(vehicle instanceof Car){
            freeSlots = this.getCarCapacity().getFreeSlots();
        }
        if(vehicle instanceof Bus){
            freeSlots = this.getBusCapacity().getFreeSlots();
        }
        if(vehicle instanceof Bike){
            freeSlots = this.getBikeCapacity().getFreeSlots();
        }
        if(vehicle instanceof Van){
            freeSlots = this.getVanCapacity().getFreeSlots();
        }
        return freeSlots;
    }
}
