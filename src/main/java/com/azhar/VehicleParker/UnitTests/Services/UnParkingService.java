package com.azhar.VehicleParker.UnitTests.Services;


import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.stereotype.Component;

@Component
public interface UnParkingService {
    ParkResponse unPark(LevelParkedVehicle levelVehicleMap);

    LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelVehicle) throws Exception;

    LevelParkedVehicle getValidLevelParkedVehicle(int levelVehicleId);

    Boolean emptySlot(LevelParkedVehicle levelParkedVehicle);

    boolean removeLevelParkedVehicle(LevelParkedVehicle levelParkedVehicle);
}
