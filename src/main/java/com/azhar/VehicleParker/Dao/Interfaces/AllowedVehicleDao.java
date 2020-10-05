package com.azhar.VehicleParker.Dao.Interfaces;

import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;

public interface AllowedVehicleDao {
    public AllowedVehicle update(AllowedVehicle allowedVehicle);
    public AllowedVehicle getAllowedVehicleByVehicleId(int parkedVehicleId);
}
