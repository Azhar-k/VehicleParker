package com.azhar.VehicleParker.UnitTests.Services;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface ParkingService {

    ParkResponse park(ParkRequest parkRequest);

    LevelParkedVehicle parkVehicle(ParkRequest parkRequest) throws Exception;

    Vehicle getVehicleByName(String name);

    int getAvailableLevelNumber(Vehicle vehicle);

    boolean fillSlot(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber);

    LevelParkedVehicle addLevelParkedVehicle(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber);

}
