package com.azhar.VehicleParker.db.repositories;


import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Vehicle findVehicleByName(String name);
}
