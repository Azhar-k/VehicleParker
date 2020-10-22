package com.azhar.VehicleParker.services.implimentation;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.Exceptions.InvalidInputException;
import com.azhar.VehicleParker.Entities.Exceptions.LevelException;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class LevelService implements com.azhar.VehicleParker.services.LevelService {

    @Autowired
    LevelDao levelDao;
    @Autowired
    VehicleDao vehicleDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public List<Level> getSortedLevels() {

        return levelDao.getLevelBySortedLevelNumber();
    }

    @Override
    public Level insertLevel(Level inputLevel) throws LevelException {
        Level insertedLevel = null;


        try {
            if(checkLevelExist(inputLevel)){
                throw new InvalidInputException("level already exist");
            }
            validateAllowedVehicles(inputLevel.getAllowedVehicles());
            insertedLevel = levelDao.insert(inputLevel);
            //allowed vehicles in inputLevel may not contain the level attribute.
            for (AllowedVehicle allowedVehicle : insertedLevel.getAllowedVehicles()) {
                allowedVehicle.setLevel(insertedLevel);
            }
            levelDao.update(insertedLevel);//update the level after changing the allowed vehicle's attribute
            logger.info("Level inserted "+insertedLevel);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input while inserting level", invalidInputException);
            throw new LevelException(invalidInputException.getMessage(),invalidInputException);
        } catch (Exception e) {
            logger.error("Exception occured while inserting level", e);
            throw new LevelException(e.getMessage(), e);
        }


        return insertedLevel;

    }

    public Boolean checkLevelExist(Level inputLevel) {
        boolean isLevelExist = true;
        Level level = levelDao.getLevelByLevelNumber(inputLevel.getNumber());
        if (level == null) {
            isLevelExist = false;
        }
        return isLevelExist;
    }

    private void validateAllowedVehicles(List<AllowedVehicle> allowedVehicles) throws Exception {
        for (AllowedVehicle allowedVehicle : allowedVehicles) {

            if(allowedVehicle.getMAX_SLOTS()<allowedVehicle.getOccupiedSlots()){
                throw new InvalidInputException("Occupied slot can not be greater than maximum slot");
            }
            Vehicle inputVehicle = allowedVehicle.getVehicle();
            Vehicle recognisedVehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
            if (recognisedVehicle == null) {
                throw new InvalidInputException("vehicle not valid");
            }
            //input vehicle from user is replaced with recognised vehicle got from database
            allowedVehicle.setVehicle(recognisedVehicle);
        }

    }

    @Override
    public boolean deleteLevel(Level inputLevel) throws LevelException {
        try {
            if(!checkLevelExist(inputLevel)){
                throw new InvalidInputException("input Level does not exist");
            }
            if(checkLevelContainVehicles(inputLevel)){
                throw new InvalidInputException("level contains vehicle");
            }
            Level level = levelDao.getLevelByLevelNumber(inputLevel.getNumber());
            levelDao.delete(level);
            logger.info("Level deleted "+inputLevel);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input while deleting level", invalidInputException);
            throw new LevelException(invalidInputException.getMessage(),invalidInputException);
        } catch (Exception e) {
            logger.error("Exception occured while deleting level", e);
            throw new LevelException(e.getMessage(),e);
        }


        return true;
    }

    public Boolean checkLevelContainVehicles(Level level) throws Exception {
        boolean isLevelContainsVehicle = false;
        level = levelDao.getLevelByLevelNumber(level.getNumber());
        for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
            if (allowedVehicle.getOccupiedSlots() > 0) {
                isLevelContainsVehicle = true;
                break;
            }
        }
        return isLevelContainsVehicle;
    }

    @Override
    public Level editLevel(Level inputLevel) throws LevelException {
        Level editedLevel=null;
        try {
            if(!checkLevelExist(inputLevel)){
                throw new InvalidInputException("input Level does not exist");
            }
            if(checkLevelContainVehicles(inputLevel)){
                throw new InvalidInputException("level contains vehicle");
            }
            validateAllowedVehicles(inputLevel.getAllowedVehicles());
            editedLevel = levelDao.update(inputLevel);
            //allowed vehicles in inputLevel may not contain the level attribute.
            for (AllowedVehicle allowedVehicle : editedLevel.getAllowedVehicles()) {
                allowedVehicle.setLevel(editedLevel);
            }
            levelDao.update(editedLevel);
            logger.info("Level edited "+editedLevel);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid argument while editing level", invalidInputException);
            throw new LevelException(invalidInputException.getMessage(),invalidInputException);
        } catch (Exception e) {
            logger.error("error while editing level", e);
            throw new LevelException(e.getMessage(),e);

        }
        return editedLevel;
    }

}
