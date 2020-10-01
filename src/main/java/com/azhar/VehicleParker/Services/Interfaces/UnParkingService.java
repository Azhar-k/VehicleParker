package com.azhar.VehicleParker.Services.Interfaces;

import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import org.springframework.stereotype.Component;

@Component
public interface UnParkingService {
    public ParkResponse unpark(LevelVehicleMap levelVehicleMap);
}
