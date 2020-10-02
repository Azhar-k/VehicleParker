package com.azhar.VehicleParker.Entities.Building;

import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelSpace {
   private int levelNumber;
   private Map<String,Integer> availabeSpace = new HashMap<String, Integer>();

    public LevelSpace(int levelNumber, Map<String,Integer> availabeSpace) {
        this.levelNumber = levelNumber;
        this.availabeSpace = availabeSpace;
    }

    public LevelSpace(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Map<String, Integer> getAvailabeSpace() {
        return availabeSpace;
    }

    public void setAvailabeSpace(Map<String,Integer> availabeSpace) {
        this.availabeSpace = availabeSpace;
    }
}
