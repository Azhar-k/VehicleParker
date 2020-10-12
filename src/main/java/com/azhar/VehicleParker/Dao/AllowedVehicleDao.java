package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;

import java.util.List;

public interface AllowedVehicleDao {
    AllowedVehicle update(AllowedVehicle allowedVehicle);

    AllowedVehicle getAllowedVehicleByVehicleId(int parkedVehicleId);

    AllowedVehicle insert(AllowedVehicle allowedVehicle);

    void delete(AllowedVehicle allowedVehicle);

    List<AllowedVehicle> getAllowedVehiclesByLevelNumber(int levelNumber);
}
