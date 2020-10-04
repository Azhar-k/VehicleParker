package com.azhar.VehicleParker.dbclient;


import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LevelParkedVehicleRepository extends JpaRepository<LevelParkedVehicle,Integer> {

}
