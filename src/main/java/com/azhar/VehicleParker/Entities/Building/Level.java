package com.azhar.VehicleParker.Entities.Building;


import javax.persistence.*;
import java.util.List;

@Entity
public class Level {
    @Id
    @GeneratedValue
    private int id;
    private int levelNumber;
    @ManyToMany(targetEntity= AllowedVehicle.class)
    private List<AllowedVehicle> allowedVehicles ;


    public Level() {
    }

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setAllowedVehicles(List<AllowedVehicle> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public List<AllowedVehicle> getAllowedVehicles() {
        return allowedVehicles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Level{" +
                "levelNumber=" + levelNumber +
                ", allowedVehicles=" + allowedVehicles +
                '}';
    }
}
