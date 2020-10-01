package com.azhar.VehicleParker.Entities.Vehicle;

public class Car extends Vehicle {


    public Car(int id, String type, int MAX_SLOTS) {
        super(id, type, MAX_SLOTS);
    }

    public Car(int id, String type) {
        super(id,type);
    }
}
