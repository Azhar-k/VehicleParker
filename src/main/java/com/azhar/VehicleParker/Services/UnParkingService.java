package com.azhar.VehicleParker.Services;


import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.stereotype.Component;

@Component
public interface UnParkingService {
    public ParkResponse unPark(LevelParkedVehicle levelVehicleMap);

    public LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelVehicle) throws Exception;

    public LevelParkedVehicle getValidLevelParkedVehicle(int levelVehicleId);

    public Boolean emptySlot(LevelParkedVehicle levelParkedVehicle);

    public boolean removeLevelParkedVehicle(LevelParkedVehicle levelParkedVehicle);
}
