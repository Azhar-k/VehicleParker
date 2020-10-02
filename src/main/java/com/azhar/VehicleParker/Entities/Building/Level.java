package com.azhar.VehicleParker.Entities.Building;


import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int levelNumber;
    private List<Vehicle> allowedVehicles = new ArrayList<Vehicle>(
    );



    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public List<Vehicle> getAllowedVehicles() {
        return allowedVehicles;
    }



}
