package com.azhar.VehicleParker.services.implimentation;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.VehicleResponse;
import com.azhar.VehicleParker.Entities.Exceptions.InvalidInputException;
import com.azhar.VehicleParker.Entities.Exceptions.VehicleException;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService implements com.azhar.VehicleParker.services.VehicleService {

    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    LevelDao levelDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleDao.getVehicleList();
    }

    @Override
    public Vehicle insertVehicle(Vehicle inputVehicle) throws VehicleException {
        Vehicle insertedVehicle = null;
        try {
            Vehicle validVehicle = validateVehicle(inputVehicle);
            if (validVehicle != null) {
                throw new InvalidInputException("vehicle already exist");
            }
            insertedVehicle = vehicleDao.insert(inputVehicle);
            logger.info("Vehicle inserted "+insertedVehicle);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input received while inserting level ", invalidInputException);
            throw new VehicleException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("Error while inserting level ", e);
            throw new VehicleException(e.getMessage(), e);
        }


        return insertedVehicle;
    }

    public Vehicle validateVehicle(Vehicle inputVehicle) {
        Vehicle vehicle = null;
        vehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
        return vehicle;
    }

    @Override
    public boolean deleteVehicle(Vehicle inputVehicle) throws VehicleException {

        try {
            Vehicle validVehicle = validateVehicle(inputVehicle);
            if (validVehicle == null) {
                throw new InvalidInputException("vehicle do not exist");
            }
            deleteVehicleFromAllLevels(validVehicle);
            vehicleDao.delete(validVehicle);
            logger.info("Level deleted "+inputVehicle);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input received while deleting level ", invalidInputException);
            throw new VehicleException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("Error while deleting level ", e);
            throw new VehicleException(e.getMessage(), e);
        }


        return true;

    }

    private void deleteVehicleFromAllLevels(Vehicle validVehicle) throws Exception {

        for (Level level : levelDao.getLevelList()) {
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                if (allowedVehicle.getVehicle().getName().equals(validVehicle.getName())) {
                    level.getAllowedVehicles().remove(allowedVehicle);
                    break;
                }
            }
        }

    }

    @Override
    public Vehicle editVehicle(Vehicle inputVehicle) throws VehicleException {
        Vehicle editedVehicle=null;
        try {
            Vehicle validVehicle = validateVehicle(inputVehicle);
            if (validVehicle == null) {
                throw new InvalidInputException("vehicle do not exist");
            }
            inputVehicle.setId(validVehicle.getId());
            editedVehicle = vehicleDao.update(inputVehicle);
            logger.info("Level inserted "+editedVehicle);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input received while editing level ", invalidInputException);
            throw new VehicleException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("Error while editing level ", e);
            throw new VehicleException(e.getMessage(), e);
        }


        return editedVehicle;
    }


}
