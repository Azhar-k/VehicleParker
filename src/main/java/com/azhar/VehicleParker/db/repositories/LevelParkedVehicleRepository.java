package com.azhar.VehicleParker.db.repositories;


import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface LevelParkedVehicleRepository extends JpaRepository<LevelParkedVehicle, Integer> {

    LevelParkedVehicle findLevelParkedVehicleById(int levelParkedVehicleId);
    @Query("SELECT DISTINCT l.date FROM LevelParkedVehicle l")
    List<LocalDate> findDistinctDate();
    @Query(value = "SELECT COUNT(*) FROM LevelParkedVehicle l WHERE l.vehicleName= ?1 AND l.date= ?2")
    int findCountByDateAndVehicleName(String vehicleName, LocalDate localDate);
}
