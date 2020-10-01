package com.azhar.VehicleParker.Entities.Vehicle;

public class Vehicle {
    private int id;
    private String type;
    private int MAX_SLOTS;
    private int occupiedSlots;

    public Vehicle(int id, String type) {
        this.id=id;
        this.type=type;

    }

    public Vehicle(String type) {
        this.type = type;
    }

    public Vehicle(int id, String type, int MAX_SLOTS) {
        this.id = id;
        this.type = type;
        this.MAX_SLOTS = MAX_SLOTS;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
