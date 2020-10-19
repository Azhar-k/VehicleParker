package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.Exceptions.ParkingException;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.stereotype.Component;

@Component
public interface ParkingService {

    LevelParkedVehicle parkVehicle(ParkRequest parkRequest) throws ParkingException;
    LevelParkedVehicle unParkVehicle(LevelParkedVehicle levelVehicleMap) throws ParkingException;


}
