package com.azhar.VehicleParker.Dao;


import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LevelRepository extends JpaRepository<Level,Integer> {

}
