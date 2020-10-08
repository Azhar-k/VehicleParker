package com.azhar.VehicleParker.UnitTests.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface VehicleService {
    VehicleResponse insertVehicle(Vehicle inputVehicle);

    VehicleResponse deleteVehicle(Vehicle inputVehicle);

    VehicleResponse editVehicle(Vehicle inputVehicle);

    Vehicle validateVehicle(Vehicle inputVehicle);
}
