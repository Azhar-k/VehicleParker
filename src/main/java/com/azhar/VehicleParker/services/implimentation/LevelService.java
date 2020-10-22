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
        return levelDao.getAllSortedByLevelNumber();
    }

    @Override
    public Level insertLevel(Level inputLevel) throws LevelException {
        Level insertedLevel;
        try {
            validateInputLevelBeforeInsert(inputLevel);
            insertedLevel = insert(inputLevel);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input while inserting level", invalidInputException);
            throw new LevelException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("Exception occured while inserting level", e);
            throw new LevelException(e.getMessage(), e);
        }
        return insertedLevel;
    }

    private void validateInputLevelBeforeInsert(Level inputLevel) throws Exception {
        if (checkLevelExist(inputLevel)) {
            throw new InvalidInputException("level already exist");
        }
        validateAllowedVehicles(inputLevel.getAllowedVehicles());
    }

    public Boolean checkLevelExist(Level inputLevel) {
        boolean isLevelExist = true;
        Level level = levelDao.getByNumber(inputLevel.getNumber());
        if (level == null) {
            isLevelExist = false;
        }
        return isLevelExist;
    }

    private void validateAllowedVehicles(List<AllowedVehicle> allowedVehicles) throws Exception {
        for (AllowedVehicle allowedVehicle : allowedVehicles) {

            if (allowedVehicle.getMAX_SLOTS() < allowedVehicle.getOccupiedSlots()) {
                throw new InvalidInputException("Occupied slot can not be greater than maximum slot");
            }
            Vehicle inputVehicle = allowedVehicle.getVehicle();
            Vehicle recognisedVehicle = vehicleDao.getByName(inputVehicle.getName());
            if (recognisedVehicle == null) {
                throw new InvalidInputException("vehicle not valid");
            }
            //input vehicle from user is replaced with recognised vehicle got from database
            allowedVehicle.setVehicle(recognisedVehicle);
        }
    }

    private Level insert(Level inputLevel) {
        Level insertedLevel = levelDao.insert(inputLevel);
        //allowed vehicles in inputLevel may not contain the level attribute.
        for (AllowedVehicle allowedVehicle : insertedLevel.getAllowedVehicles()) {
            allowedVehicle.setLevel(insertedLevel);
        }
        levelDao.update(insertedLevel);//update the level after changing the allowed vehicle's attribute
        logger.info("Level inserted " + insertedLevel);

        return insertedLevel;
    }

    @Override
    public boolean deleteLevel(Level inputLevel) throws LevelException {
        try {
            validateInputLevel(inputLevel);
            delete(inputLevel);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input while deleting level", invalidInputException);
            throw new LevelException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("Exception occured while deleting level", e);
            throw new LevelException(e.getMessage(), e);
        }
        return true;
    }

    private void validateInputLevel(Level inputLevel) throws Exception {
        if (!checkLevelExist(inputLevel)) {
            throw new InvalidInputException("input Level does not exist");
        }
        if (checkLevelContainVehicles(inputLevel)) {
            throw new InvalidInputException("level contains vehicle");
        }
    }

    public Boolean checkLevelContainVehicles(Level level) throws Exception {
        boolean isLevelContainsVehicle = false;
        level = levelDao.getByNumber(level.getNumber());
        for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
            if (allowedVehicle.getOccupiedSlots() > 0) {
                isLevelContainsVehicle = true;
                break;
            }
        }
        return isLevelContainsVehicle;
    }

    private void delete(Level inputLevel) {
        Level level = levelDao.getByNumber(inputLevel.getNumber());
        levelDao.delete(level);
        logger.info("Level deleted " + inputLevel);
    }

    @Override
    public Level editLevel(Level inputLevel) throws LevelException {
        Level editedLevel;
        try {
            validateInputLevel(inputLevel);
            validateAllowedVehicles(inputLevel.getAllowedVehicles());
            editedLevel = edit(inputLevel);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid argument while editing level", invalidInputException);
            throw new LevelException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("error while editing level", e);
            throw new LevelException(e.getMessage(), e);

        }
        return editedLevel;
    }

    private Level edit(Level inputLevel) {
        Level editedLevel = levelDao.update(inputLevel);
        //allowed vehicles in inputLevel may not contain the level attribute.
        for (AllowedVehicle allowedVehicle : editedLevel.getAllowedVehicles()) {
            allowedVehicle.setLevel(editedLevel);
        }
        levelDao.update(editedLevel);
        logger.info("Level edited " + editedLevel);
        return editedLevel;
    }

}
