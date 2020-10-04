package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.Entities.LevelParkedVehicle;

public class ParkResponse {
    private boolean isSucces;
    private String message;
    private LevelParkedVehicle levelVehicleMap;

    public ParkResponse(boolean isSucces, String message, LevelParkedVehicle levelVehicleMap) {
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

    public LevelParkedVehicle getVehicleMap() {
        return levelVehicleMap;
    }

    public void setVehicleMap(LevelParkedVehicle vehicleMap) {
        this.levelVehicleMap = vehicleMap;
    }
}
