package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.Entities.LevelVehicle;

public class ParkResponse {
    private boolean isSucces;
    private String message;
    private LevelVehicle vehicleMap;

    public ParkResponse(boolean isSucces, String message, LevelVehicle levelVehicleMap) {
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

    public LevelVehicle getVehicleMap() {
        return vehicleMap;
    }

    public void setVehicleMap(LevelVehicle vehicleMap) {
        this.vehicleMap = vehicleMap;
    }

    @Override
    public String toString() {
        return "ParkResponse{" +
                "isSucces=" + isSucces +
                ", message='" + message + '\'' +
                ", vehicleMap=" + vehicleMap +
                '}';
    }
}
