package com.azhar.VehicleParker.db.dbClients;


import com.azhar.VehicleParker.db.entities.Vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    public Vehicle findVehicleByName(String name);
}
