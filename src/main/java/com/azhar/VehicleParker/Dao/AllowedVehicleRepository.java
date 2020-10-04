package com.azhar.VehicleParker.Dao;


import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Building.Level;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AllowedVehicleRepository extends JpaRepository<AllowedVehicle,Integer> {

}
