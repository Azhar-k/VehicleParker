package com.azhar.VehicleParker.Entities;

//map a vehicle to a level. Id will be unique to this parking
public class LevelVehicle {
    private int id;
    private int levelNumber;
    private String vehicleType;

    public LevelVehicle(int levelNumber, String vehicleType) {
        this.levelNumber = levelNumber;
        this.vehicleType = vehicleType;
    }

    public LevelVehicle(int id) {
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

    @Override
    public String toString() {
        return "LevelVehicleMap{" +
                "id=" + id +
                ", levelNumber=" + levelNumber +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
