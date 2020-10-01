package com.azhar.VehicleParker.Entities.Building;


import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

public class Level {
    private int levelNumber;
    private LevelCapacity levelCapacity;

    public Level(int levelNumber, LevelCapacity levelCapacity) {
        this.levelNumber = levelNumber;
        this.levelCapacity = levelCapacity;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public LevelCapacity getLevelCapacity() {
        return levelCapacity;
    }

    public void setLevelCapacity(LevelCapacity levelCapacity) {
        this.levelCapacity = levelCapacity;
    }


}
