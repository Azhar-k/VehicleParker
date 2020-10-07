package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface ParkingService {

    public ParkResponse park(ParkRequest parkRequest);

    public LevelParkedVehicle parkVehicle(ParkRequest parkRequest) throws Exception;

    public Vehicle getVehicleByName(String name);

    public int getAvailableLevelNumber(Vehicle vehicle);

    public boolean fillSlot(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber);

    public LevelParkedVehicle addLevelParkedVehicle(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber);

}
