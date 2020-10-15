package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
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
    public List<Vehicle> getVehicleList() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleByName(String name) throws VehicleNotFound{

        Vehicle vehicle = null;
        try {
            vehicle = vehicleRepository.findVehicleByName(name);
        } catch (Exception e) {
            throw new VehicleNotFound("Vehicle not exist");
        }
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
