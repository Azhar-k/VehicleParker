package com.azhar.VehicleParker.Services.Interfaces;


import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.stereotype.Component;

@Component
public interface UnParkingService {
    public ParkResponse unPark(LevelVehicle levelVehicleMap);
    public LevelVehicle unParkVehicle(LevelVehicle inputLevelVehicle) throws Exception;
    public LevelVehicle getValidLevelVehicle(int levelVehicleId);
}
