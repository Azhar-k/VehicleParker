package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

import java.util.List;

public interface VehicleDao {

    public List<Vehicle> getVehicleList();

    Vehicle getVehicleByName(String name);

    public Vehicle findById(int id);
}
