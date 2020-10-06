package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
import com.azhar.VehicleParker.db.entities.Vehicle.Vehicle;

import java.util.List;

public interface VehicleDao {

    public List<Vehicle> getVehicleList();

    Vehicle getVehicleByName(String name);

    public Vehicle findById(int id);

    public Vehicle getVehicleById(int id) throws VehicleNotFound;

    public Vehicle insert(Vehicle inputVehicle);

    public Vehicle update(Vehicle inputVehicle);

    public void delete(Vehicle inputVehicle);
}
