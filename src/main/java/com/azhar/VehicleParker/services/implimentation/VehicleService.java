package com.azhar.VehicleParker.services.implimentation;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements com.azhar.VehicleParker.services.VehicleService {

    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    LevelDao levelDao;

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleDao.getVehicleList();
    }

    @Override
    public VehicleResponse insertVehicle(Vehicle inputVehicle) {
        VehicleResponse editVehicleResponse;
        Vehicle validVehicle = validateVehicle(inputVehicle);
        if (validVehicle == null) {
            try {
                Vehicle vehicle = vehicleDao.insert(inputVehicle);
                editVehicleResponse = new VehicleResponse(true, "vehicle added", vehicle);

            } catch (Exception e) {
                e.printStackTrace();
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
                deleteVehicleFromAllLevels(validVehicle);
                vehicleDao.delete(validVehicle);
                editVehicleResponse = new VehicleResponse(true, "vehicle deleted", validVehicle);

            } catch (Exception e) {
                e.printStackTrace();
                editVehicleResponse = new VehicleResponse(true, "something went wrong " + e.getMessage(), null);
            }

        } else {
            editVehicleResponse = new VehicleResponse(true, "vehicle do not exist", null);
        }
        return editVehicleResponse;

    }

    private void deleteVehicleFromAllLevels(Vehicle validVehicle) throws Exception {
        try {
            for(Level level:levelDao.getLevelList()){
                for(AllowedVehicle allowedVehicle:level.getAllowedVehicles()){
                    if(allowedVehicle.getVehicle().getName().equals(validVehicle.getName())){
                        level.getAllowedVehicles().remove(allowedVehicle);
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("vehicle can not be deleted. Some error occured");
        }

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
        Vehicle vehicle=null;
        try {
            vehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return vehicle;
    }
}
