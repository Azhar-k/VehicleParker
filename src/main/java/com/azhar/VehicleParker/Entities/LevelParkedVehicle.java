package com.azhar.VehicleParker.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//map a vehicle to a level. Id will be unique to this maping
@Entity
public class LevelParkedVehicle {
    @Id
    @GeneratedValue
    private int id;
    private int levelNumber;
    private int vehicleType;
    private String vehicleName;
    private String vehicleNumber;

    public LevelParkedVehicle(int levelNumber, int vehicleType) {
        this.levelNumber = levelNumber;
        this.vehicleType = vehicleType;
    }

    public LevelParkedVehicle( int levelNumber, int vehicleType, String vehicleName, String vehicleNumber) {

        this.levelNumber = levelNumber;
        this.vehicleType = vehicleType;
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
    }


    public LevelParkedVehicle() {
    }

    public LevelParkedVehicle(int id) {
        this.id = id;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return  this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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
