package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.dbclient.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class VehicleDao implements com.azhar.VehicleParker.Dao.VehicleDao {
    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getVehicleList() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleByName(String name) {
        return vehicleRepository.findVehicleByName(name);
    }

    @Override
    public Vehicle findById(int id) {
        return vehicleRepository.getOne(id);
    }
}
