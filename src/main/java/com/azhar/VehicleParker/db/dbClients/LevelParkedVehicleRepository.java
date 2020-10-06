package com.azhar.VehicleParker.db.dbClients;


import com.azhar.VehicleParker.db.entities.LevelParkedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LevelParkedVehicleRepository extends JpaRepository<LevelParkedVehicle, Integer> {

    public LevelParkedVehicle findLevelParkedVehicleById(int levelParkedVehicleId);
}
