package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface ParkingService {

    ParkResponse park(ParkRequest parkRequest);

    LevelParkedVehicle parkVehicle(ParkRequest parkRequest) throws Exception;

    Vehicle getVehicleByName(String name) throws Exception;

    int getAvailableLevelNumber(Vehicle vehicle);

    boolean fillSlot(int levelNumber, int parkedVehicleId);

    LevelParkedVehicle addLevelParkedVehicle(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber, int parkingRate);

    ParkResponse unPark(LevelParkedVehicle levelVehicleMap);

    LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelVehicle) throws Exception;

    LevelParkedVehicle getValidLevelParkedVehicle(int levelVehicleId);

    Boolean emptySlot(LevelParkedVehicle levelParkedVehicle);

    boolean removeLevelParkedVehicle(LevelParkedVehicle levelParkedVehicle);

}
