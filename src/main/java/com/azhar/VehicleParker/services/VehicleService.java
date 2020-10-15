package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VehicleService {
    List<Vehicle> getVehicles();

    VehicleResponse insertVehicle(Vehicle inputVehicle);

    VehicleResponse deleteVehicle(Vehicle inputVehicle);

    VehicleResponse editVehicle(Vehicle inputVehicle);

    Vehicle validateVehicle(Vehicle inputVehicle);
}
