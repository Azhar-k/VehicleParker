package com.azhar.VehicleParker.db.repositories;


import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AllowedVehicleRepository extends JpaRepository<AllowedVehicle, Integer> {

    AllowedVehicle findAllowedVehicleByVehicleId(int id);

    List<AllowedVehicle> findByLevelNumber(int levelNumber);
}
