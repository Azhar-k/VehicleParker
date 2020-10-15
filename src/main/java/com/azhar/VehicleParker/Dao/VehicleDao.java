package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> getVehicleList();

    Vehicle getVehicleByName(String name) throws VehicleNotFound;

    Vehicle insert(Vehicle inputVehicle);

    Vehicle update(Vehicle inputVehicle);

    boolean delete(Vehicle inputVehicle);
}
