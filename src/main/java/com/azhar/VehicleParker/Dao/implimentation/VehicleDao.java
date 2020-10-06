package com.azhar.VehicleParker.Dao.implimentation;

import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
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

    @Override
    public Vehicle getVehicleById(int id) throws VehicleNotFound {
        Vehicle vehicle=null;
        try {
            vehicle= vehicleRepository.getOne(id);
        }catch (Exception e){
            throw new VehicleNotFound("Vehicle not exist");
        }
        return vehicle;
    }

    @Override
    public Vehicle insert(Vehicle inputVehicle) {
        return vehicleRepository.save(inputVehicle);
    }
}
