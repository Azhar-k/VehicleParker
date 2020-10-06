package com.azhar.VehicleParker.db.entities.Building;


import javax.persistence.*;
import java.util.List;

@Entity
public class Level {
    @Id
    private int levelNumber;
    @ManyToMany(targetEntity = AllowedVehicle.class)
    private List<AllowedVehicle> allowedVehicles;


    public Level() {
    }

    public Level(int id, int levelNumber, List<AllowedVehicle> allowedVehicles) {
        this.levelNumber = levelNumber;
        this.allowedVehicles = allowedVehicles;
    }

    public Level(int levelNumber, List<AllowedVehicle> allowedVehicles) {
        this.levelNumber = levelNumber;
        this.allowedVehicles = allowedVehicles;
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


    @Override
    public String toString() {
        return "Level{" +
                "levelNumber=" + levelNumber +
                ", allowedVehicles=" + allowedVehicles +
                '}';
    }
}
