package com.azhar.VehicleParker.Entities.Vehicle;

public class Vehicle {
    private int MAX_SLOTS;
    private int occupiedSlots;
    private int freeSlots;
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

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getFreeSlots(){
        this.freeSlots =this.getMAX_SLOTS()-this.getOccupiedSlots();
        return this.freeSlots;
    }

    public void setOccupiedSlots(int occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public void setMAX_SLOTS(int MAX_SLOTS) {
        this.MAX_SLOTS = MAX_SLOTS;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "MAX_SLOTS=" + MAX_SLOTS +
                ", occupiedSlots=" + occupiedSlots +
                ", freeSlots=" + freeSlots +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
