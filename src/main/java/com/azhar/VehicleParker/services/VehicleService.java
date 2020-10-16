package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.Exceptions.VehicleException;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VehicleService {
    List<Vehicle> getVehicles();

    Vehicle insertVehicle(Vehicle inputVehicle) throws VehicleException;

    boolean deleteVehicle(Vehicle inputVehicle) throws VehicleException;

    Vehicle editVehicle(Vehicle inputVehicle) throws VehicleException;
}
