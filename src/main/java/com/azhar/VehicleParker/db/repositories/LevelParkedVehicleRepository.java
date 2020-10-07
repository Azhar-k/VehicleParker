package com.azhar.VehicleParker.db.repositories;


import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LevelParkedVehicleRepository extends JpaRepository<LevelParkedVehicle, Integer> {

    public LevelParkedVehicle findLevelParkedVehicleById(int levelParkedVehicleId);
}
