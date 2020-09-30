package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    public LevelVehicleMap park(Vehicle vehicle){
        return new LevelVehicleMap(1,vehicle.getType());
    }
}
