package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements com.azhar.VehicleParker.Services.VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public VehicleResponse insertVehicle(Vehicle inputVehicle) {
        VehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if (validVehicle == null) {
            try {
                Vehicle vehicle = vehicleDao.insert(inputVehicle);
                editVehicleResponse = new VehicleResponse(true, "vehicle added", vehicle);

            } catch (Exception e) {
                editVehicleResponse = new VehicleResponse(true, e.getMessage(), null);
            }

        } else {
            editVehicleResponse = new VehicleResponse(true, "vehicle already exist", validVehicle);
        }
        return editVehicleResponse;
    }

    @Override
    public VehicleResponse deleteVehicle(Vehicle inputVehicle) {
        VehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if (validVehicle != null) {
            try {
                vehicleDao.delete(validVehicle);
                editVehicleResponse = new VehicleResponse(true, "vehicle deleted", validVehicle);

            } catch (Exception e) {
                editVehicleResponse = new VehicleResponse(true, "something went wrong " + e.getMessage(), null);
            }

        } else {
            editVehicleResponse = new VehicleResponse(true, "vehicle do not exist", null);
        }
        return editVehicleResponse;

    }

    @Override
    public VehicleResponse editVehicle(Vehicle inputVehicle) {
        VehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if (validVehicle != null) {
            try {
                inputVehicle.setId(validVehicle.getId());
                Vehicle vehicle = vehicleDao.update(inputVehicle);
                editVehicleResponse = new VehicleResponse(true, "vehicle edited", vehicle);

            } catch (Exception e) {
                editVehicleResponse = new VehicleResponse(true, "something went wrong " + e.getMessage(), null);
            }

        } else {
            editVehicleResponse = new VehicleResponse(true, "vehicle do not exist", null);
        }
        return editVehicleResponse;
    }

    @Override
    public Vehicle validateVehicle(Vehicle inputVehicle) {
        Vehicle vehicle = vehicleDao.getVehicleByName(inputVehicle.getName());

        return vehicle;
    }
}
