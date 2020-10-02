package com.azhar.VehicleParker.Entities.Building;


import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Level {
    private int levelNumber;
    private Map<Integer, Vehicle> allowedVehicles = new HashMap<Integer, Vehicle>();



    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Map<Integer, Vehicle> getAllowedVehicles() {
        return allowedVehicles;
    }



}
