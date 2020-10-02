package com.azhar.VehicleParker.Entities.Responses;

import com.azhar.VehicleParker.Entities.LevelVehicle;

public class ParkResponse {
    private boolean isSucces;
    private String message;
    private LevelVehicle levelVehicleMap;

    public ParkResponse(boolean isSucces, String message,LevelVehicle levelVehicleMap) {
        this.isSucces = isSucces;
        this.message = message;
        this.levelVehicleMap = levelVehicleMap;
    }

    public boolean isSucces() {
        return isSucces;
    }

    public void setSucces(boolean succes) {
        isSucces = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LevelVehicle getVehicleMap() {
        return levelVehicleMap;
    }

    public void setVehicleMap(LevelVehicle vehicleMap) {
        this.levelVehicleMap = vehicleMap;
    }
}
