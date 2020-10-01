package com.azhar.VehicleParker.Services.Interfaces;

import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.stereotype.Component;

@Component
public interface UnParkingService {
    public ParkResponse unpark(LevelVehicle levelVehicleMap);
}
