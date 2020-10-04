package com.azhar.VehicleParker.Services.Interfaces;

import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface ParkingService {

    public ParkResponse park(Vehicle vehicle);
    public LevelParkedVehicle parkVehicle(Vehicle vehicle) throws Exception;
    public Vehicle getVehicle(String name);
    public int getAvailableLevelNumber(Vehicle vehicle);

}
