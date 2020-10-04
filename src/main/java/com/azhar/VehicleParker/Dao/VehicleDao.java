package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;

import java.util.List;

public interface VehicleDao {
    public void update(AllowedVehicle allowedVehicle);
    public List<Vehicle> getVehicleList();
}
