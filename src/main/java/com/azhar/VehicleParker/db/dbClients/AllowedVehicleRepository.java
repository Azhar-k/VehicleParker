package com.azhar.VehicleParker.db.dbClients;


import com.azhar.VehicleParker.db.entities.Building.AllowedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AllowedVehicleRepository extends JpaRepository<AllowedVehicle, Integer> {

    public AllowedVehicle findAllowedVehicleByVehicleId(int id);
}
