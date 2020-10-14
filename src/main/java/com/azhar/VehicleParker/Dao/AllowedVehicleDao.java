package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;

public interface AllowedVehicleDao {
    AllowedVehicle update(AllowedVehicle allowedVehicle);

    AllowedVehicle getAllowedVehicleByVehicleId(int parkedVehicleId);

    AllowedVehicle insert(AllowedVehicle allowedVehicle);

    boolean delete(AllowedVehicle allowedVehicle);
}
