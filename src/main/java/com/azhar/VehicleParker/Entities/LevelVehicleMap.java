package com.azhar.VehicleParker.Entities;


import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

public class LevelVehicleMap {
    private int levelNumber;
    private Vehicle vehicle;

    public LevelVehicleMap(int levelNumber, Vehicle vehicleType) {
        this.levelNumber = levelNumber;
        this.vehicle = vehicleType;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
