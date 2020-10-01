package com.azhar.VehicleParker.Entities.Level;

//represent level number and available spaces of each type
public class LevelSpace {
   private int levelNumber,availableCarSpace,availableBusSpace,availableVanSpace,availableBikeSpace;

    public LevelSpace(int levelNumber, int availableCarSpace, int availableBusSpace, int availableVanSpace, int availableBikeSpace) {
        this.levelNumber = levelNumber;
        this.availableCarSpace = availableCarSpace;
        this.availableBusSpace = availableBusSpace;
        this.availableVanSpace = availableVanSpace;
        this.availableBikeSpace = availableBikeSpace;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getAvailableCarSpace() {
        return availableCarSpace;
    }

    public void setAvailableCarSpace(int availableCarSpace) {
        this.availableCarSpace = availableCarSpace;
    }

    public int getAvailableBusSpace() {
        return availableBusSpace;
    }

    public void setAvailableBusSpace(int availableBusSpace) {
        this.availableBusSpace = availableBusSpace;
    }

    public int getAvailableVanSpace() {
        return availableVanSpace;
    }

    public void setAvailableVanSpace(int availableVanSpace) {
        this.availableVanSpace = availableVanSpace;
    }

    public int getAvailableBikeSpace() {
        return availableBikeSpace;
    }

    public void setAvailableBikeSpace(int availableBikeSpace) {
        this.availableBikeSpace = availableBikeSpace;
    }
}
