package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.dbclient.AllowedVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllowedVehicleDao implements com.azhar.VehicleParker.Dao.Interfaces.AllowedVehicleDao {
    @Autowired
    AllowedVehicleRepository allowedVehicleRepository;

    @Override
    public AllowedVehicle update(AllowedVehicle allowedVehicle){
        return allowedVehicleRepository.save(allowedVehicle);
    }

    @Override
    public AllowedVehicle getAllowedVehicleByVehicleId(int parkedVehicleId) {
        return allowedVehicleRepository.findAllowedVehicleByVehicleId(parkedVehicleId   );
    }
}
