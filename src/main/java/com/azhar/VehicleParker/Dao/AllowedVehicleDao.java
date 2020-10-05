package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;

public interface AllowedVehicleDao {
    public AllowedVehicle update(AllowedVehicle allowedVehicle);
    public AllowedVehicle getAllowedVehicleByVehicleId(int parkedVehicleId);

    public AllowedVehicle insert(AllowedVehicle allowedVehicle);
}
