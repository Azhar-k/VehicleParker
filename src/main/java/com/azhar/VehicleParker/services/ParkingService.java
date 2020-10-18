package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface ParkingService {

    ParkResponse park(ParkRequest parkRequest);
    ParkResponse unPark(LevelParkedVehicle levelVehicleMap);


}
