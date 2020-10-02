package com.azhar.VehicleParker.Entities.Vehicle;

public class Vehicle {
    private int MAX_SLOTS;
    private int occupiedSlots;
    private int type;
    private String name;


    public Vehicle(String name) {
        this.name = name;
    }



    public Vehicle(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public Vehicle(int type, String name, int MAX_SLOTS) {
        this.MAX_SLOTS = MAX_SLOTS;
        this.type = type;
        this.name = name;
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

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFreeSlots(){
        return (this.getMAX_SLOTS()-this.getOccupiedSlots());
    }
}
