package com.azhar.VehicleParker.Dao.Interfaces;

import com.azhar.VehicleParker.Entities.LevelParkedVehicle;

import java.util.List;

public interface LevelParkedVehicleDao {

   public LevelParkedVehicle insert(LevelParkedVehicle levelAllowedVehicle);
   public List<LevelParkedVehicle> getLevelParkedVehicleList();
   public void delete(LevelParkedVehicle levelAllowedVehicle);

   public LevelParkedVehicle getLevelParkedVehicleById(int levelParkedVehicleId);
}