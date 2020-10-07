package com.azhar.VehicleParker.Entities;

import java.util.HashMap;
import java.util.Map;

//represent level number and available spaces of each type of vehicle in that level
public class LevelSpace {
    private int levelNumber;
    private Map<String, Integer> availabeSlots = new HashMap<String, Integer>();

    public LevelSpace(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Map<String, Integer> getAvailabeSlots() {
        return availabeSlots;
    }

    public void setAvailabeSlots(Map<String, Integer> availabeSlots) {
        this.availabeSlots = availabeSlots;
    }
}
