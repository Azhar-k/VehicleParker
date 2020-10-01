package com.azhar.VehicleParker.Entities;


import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

public class LevelVehicleMap {
    private int levelNumber;
    private int vehicleId;

    public LevelVehicleMap(int levelNumber, int vehicleId) {
        this.levelNumber = levelNumber;
        this.vehicleId = vehicleId;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getVehicle() {
        return vehicleId;
    }

    public void setVehicle(int vehicleID) {
        this.vehicleId = vehicleID;
    }
}
