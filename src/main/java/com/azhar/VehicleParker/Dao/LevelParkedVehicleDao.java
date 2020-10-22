package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.db.models.LevelParkedVehicle;

import java.time.LocalDate;
import java.util.List;

public interface LevelParkedVehicleDao {

    LevelParkedVehicle insert(LevelParkedVehicle levelAllowedVehicle);

    List<LevelParkedVehicle> getAll();

    void delete(LevelParkedVehicle levelAllowedVehicle);

    LevelParkedVehicle getById(int levelParkedVehicleId);

    List<LocalDate> getDistinctDate();

    int findCountByDateAndVehicleName(String vehicleName, LocalDate localDate);
}
