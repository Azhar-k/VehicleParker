package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import com.azhar.VehicleParker.db.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleDao implements com.azhar.VehicleParker.Dao.VehicleDao {
    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getByName(String name){

        Vehicle vehicle = vehicleRepository.findVehicleByName(name);
        return vehicle;
    }

    @Override
    public Vehicle insert(Vehicle inputVehicle) {
        return vehicleRepository.save(inputVehicle);
    }

    @Override
    public Vehicle update(Vehicle inputVehicle) {
        return vehicleRepository.save(inputVehicle);
    }

    @Override
    public boolean delete(Vehicle inputVehicle) {
        vehicleRepository.delete(inputVehicle);
        return true;
    }
}
