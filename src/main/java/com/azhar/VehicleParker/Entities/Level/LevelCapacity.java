package com.azhar.VehicleParker.Entities.Level;

public class LevelCapacity {
    private int MAX_NUMBER_OF_CAR = 10;
    private int MAX_NUMBER_OF_BUS = 10;
    private int MAX_NUMBER_OF_BIKE= 10;
    private int MAX_NUMBER_OF_VAN= 10;

    private int occupied_car_slots;
    private int occupied_bus_slots;
    private int occupied_bike_slots;
    private int occupied_van_slots;

    public LevelCapacity(int MAX_NUMBER_OF_CAR, int MAX_NUMBER_OF_BUS, int MAX_NUMBER_OF_BIKE, int MAX_NUMBER_OF_VAN) {
        this.MAX_NUMBER_OF_CAR = MAX_NUMBER_OF_CAR;
        this.MAX_NUMBER_OF_BUS = MAX_NUMBER_OF_BUS;
        this.MAX_NUMBER_OF_BIKE = MAX_NUMBER_OF_BIKE;
        this.MAX_NUMBER_OF_VAN = MAX_NUMBER_OF_VAN;

    }

    public int getMAX_NUMBER_OF_CAR() {
        return MAX_NUMBER_OF_CAR;
    }

    public void setMAX_NUMBER_OF_CAR(int MAX_NUMBER_OF_CAR) {
        this.MAX_NUMBER_OF_CAR = MAX_NUMBER_OF_CAR;
    }

    public int getMAX_NUMBER_OF_BUS() {
        return MAX_NUMBER_OF_BUS;
    }

    public void setMAX_NUMBER_OF_BUS(int MAX_NUMBER_OF_BUS) {
        this.MAX_NUMBER_OF_BUS = MAX_NUMBER_OF_BUS;
    }

    public int getMAX_NUMBER_OF_BIKE() {
        return MAX_NUMBER_OF_BIKE;
    }

    public void setMAX_NUMBER_OF_BIKE(int MAX_NUMBER_OF_BIKE) {
        this.MAX_NUMBER_OF_BIKE = MAX_NUMBER_OF_BIKE;
    }

    public int getMAX_NUMBER_OF_VAN() {
        return MAX_NUMBER_OF_VAN;
    }

    public void setMAX_NUMBER_OF_VAN(int MAX_NUMBER_OF_VAN) {
        this.MAX_NUMBER_OF_VAN = MAX_NUMBER_OF_VAN;
    }

    public int getOccupied_car_slots() {
        return occupied_car_slots;
    }

    public void setOccupied_car_slots(int occupied_car_slots) {
        this.occupied_car_slots = occupied_car_slots;
    }

    public int getOccupied_bus_slots() {
        return occupied_bus_slots;
    }

    public void setOccupied_bus_slots(int occupied_bus_slots) {
        this.occupied_bus_slots = occupied_bus_slots;
    }

    public int getOccupied_bike_slots() {
        return occupied_bike_slots;
    }

    public void setOccupied_bike_slots(int occupied_bike_slots) {
        this.occupied_bike_slots = occupied_bike_slots;
    }

    public int getOccupied_van_slots() {
        return occupied_van_slots;
    }

    public void setOccupied_van_slots(int occupied_van_slots) {
        this.occupied_van_slots = occupied_van_slots;
    }
}
