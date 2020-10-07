package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.db.models.LevelParkedVehicle;

//Used to provide responses for all services of ParkService and UnParkService
public class ParkResponse extends Response {

    private LevelParkedVehicle levelVehicleMap;

    public ParkResponse(boolean isSucces, String message, LevelParkedVehicle levelVehicleMap) {
        super(isSucces, message);
        this.levelVehicleMap = levelVehicleMap;
    }

    public LevelParkedVehicle getVehicleMap() {
        return levelVehicleMap;
    }

    public void setVehicleMap(LevelParkedVehicle vehicleMap) {
        this.levelVehicleMap = vehicleMap;
    }
}
