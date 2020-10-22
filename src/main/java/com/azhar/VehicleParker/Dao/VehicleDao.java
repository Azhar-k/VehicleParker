package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> getAll();

    Vehicle getByName(String name);

    Vehicle insert(Vehicle inputVehicle);

    Vehicle update(Vehicle inputVehicle);

    boolean delete(Vehicle inputVehicle);
}
