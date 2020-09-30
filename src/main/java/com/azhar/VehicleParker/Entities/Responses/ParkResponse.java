package com.azhar.VehicleParker.Entities.Responses;

import com.azhar.VehicleParker.Entities.LevelVehicleMap;

public class ParkResponse {
    private boolean isSucces;
    private String message;
    private LevelVehicleMap vehicleMap;

    public ParkResponse(boolean isSucces, String message,LevelVehicleMap levelVehicleMap) {
        this.isSucces = isSucces;
        this.message = message;
        this.vehicleMap = levelVehicleMap;
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

    public LevelVehicleMap getVehicleMap() {
        return vehicleMap;
    }

    public void setVehicleMap(LevelVehicleMap vehicleMap) {
        this.vehicleMap = vehicleMap;
    }
}
