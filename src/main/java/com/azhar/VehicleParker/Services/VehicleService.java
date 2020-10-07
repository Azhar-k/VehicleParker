package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface VehicleService {
    public VehicleResponse insertVehicle(Vehicle inputVehicle);

    public VehicleResponse deleteVehicle(Vehicle inputVehicle);

    public VehicleResponse editVehicle(Vehicle inputVehicle);

    public Vehicle validateVehicle(Vehicle inputVehicle);
}
