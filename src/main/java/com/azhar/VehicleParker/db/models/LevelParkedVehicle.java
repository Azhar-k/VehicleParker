package com.azhar.VehicleParker.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

//map a vehicle to a level. Id will be unique to this maping
@Entity
public class LevelParkedVehicle {
    @Id
    @GeneratedValue
    private int id;
    private int levelNumber;
    private int vehicleType;
    private String vehicleName;
    @Column(unique=true)
    private String vehicleNumber;
    private LocalDate date;
    private LocalTime time;

    public LevelParkedVehicle(int levelNumber, int vehicleType, String vehicleName, String vehicleNumber) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
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
        return this.id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
