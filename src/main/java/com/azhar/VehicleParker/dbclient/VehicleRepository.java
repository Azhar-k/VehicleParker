package com.azhar.VehicleParker.dbclient;


import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

   public Vehicle findVehicleByName(String name);
}
