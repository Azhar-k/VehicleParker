package com.azhar.VehicleParker.Entities.Vehicle;

public class Vehicle {
    private int MAX_SLOTS;
    private int occupiedSlots;
    private int id;


    public Vehicle(int id, int MAX_SLOTS) {
        this.id=id;
        this.MAX_SLOTS = MAX_SLOTS;
    }

    public Vehicle() {
    }

    public int getMAX_SLOTS() {
        return MAX_SLOTS;
    }

    public void setMAX_SLOTS(int MAX_SLOTS) {
        this.MAX_SLOTS = MAX_SLOTS;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public void setOccupiedSlots(int occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getFreeSlots(){
        return (this.getMAX_SLOTS()-this.getOccupiedSlots());
    }
}
