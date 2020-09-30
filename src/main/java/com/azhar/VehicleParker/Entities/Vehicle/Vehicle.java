package com.azhar.VehicleParker.Entities.Vehicle;

public class Vehicle {
    private int id;
    private String type;

    public Vehicle(int id, String type) {
        this.type=type;

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
