package com.azhar.VehicleParker.Entities;


public class LevelVehicleMap {
    private int id;
    private int levelNumber;
    private String vehicleType;

    public LevelVehicleMap(int levelNumber, String vehicleType) {
        this.levelNumber = levelNumber;
        this.vehicleType = vehicleType;
    }

    public LevelVehicleMap(int id) {
        this.id = id;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return  this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
