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

    public LevelParkedVehicle(int levelNumber, int vehicleType) {
        this.levelNumber = levelNumber;
        this.vehicleType = vehicleType;
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

    @Override
    public String toString() {
        return "LevelVehicleMap{" +
                "id=" + id +
                ", levelNumber=" + levelNumber +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
