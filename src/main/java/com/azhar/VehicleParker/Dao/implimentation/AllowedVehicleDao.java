package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.repositories.AllowedVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllowedVehicleDao implements com.azhar.VehicleParker.Dao.AllowedVehicleDao {
    @Autowired
    AllowedVehicleRepository allowedVehicleRepository;

    @Override
    public AllowedVehicle update(AllowedVehicle allowedVehicle) {
        return allowedVehicleRepository.save(allowedVehicle);
    }


    @Override
    public AllowedVehicle insert(AllowedVehicle allowedVehicle) {
        return allowedVehicleRepository.save(allowedVehicle);
    }

    @Override
    public boolean delete(AllowedVehicle allowedVehicle) {
        allowedVehicleRepository.delete(allowedVehicle);
        return true;
    }

}
