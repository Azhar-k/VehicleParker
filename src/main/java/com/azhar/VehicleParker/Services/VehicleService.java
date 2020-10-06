package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.EditVehicleResponse;
import com.azhar.VehicleParker.db.entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

@Component
public interface VehicleService {
    public EditVehicleResponse insertVehicle(Vehicle inputVehicle);

    public EditVehicleResponse deleteVehicle(Vehicle inputVehicle);

    public EditVehicleResponse editVehicle(Vehicle inputVehicle);

    public Vehicle validateVehicle(Vehicle inputVehicle);
}
