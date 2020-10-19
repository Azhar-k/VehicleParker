package com.azhar.VehicleParker.services.implimentation;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.isTrue;


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
    public LevelResponse insertLevel(Level inputLevel) {
        LevelResponse levelResponse = null;


        try {
            checkLevelExist(inputLevel);
            //input vehicle should be validated before editing.User may try to add non recognised vehicle
            validateVehicles(inputLevel.getAllowedVehicles());
            Level level = levelDao.insert(inputLevel);
            //allowed vehicles in inputLevel may not contain the level attribute.
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                allowedVehicle.setLevel(level);
            }
            levelDao.update(level);//update the level after changing the allowed vehicle's attribute
            levelResponse = new LevelResponse(true, "Level added", level);
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.info("Invalid argument while inserting level", illegalArgumentException);
            String errorMessage = illegalArgumentException.getMessage();
            levelResponse = new LevelResponse(false, errorMessage, null);
        } catch (Exception e) {
            logger.error("Exception occured while inserting level", e);
            String errorMessage = "Some error occured";
            levelResponse = new LevelResponse(false, errorMessage, null);
        }


        return levelResponse;

    }

    public Boolean checkLevelExist(Level inputLevel) throws Exception {
        boolean isLevelExist = true;
        Level level = levelDao.getLevelByLevelNumber(inputLevel.getNumber());
        if (level == null) {
            throw new IllegalArgumentException("level already exist");
        }
        return isLevelExist;
    }

    private void validateVehicles(List<AllowedVehicle> allowedVehicles) throws Exception {
        for (AllowedVehicle allowedVehicle : allowedVehicles) {

            Vehicle inputVehicle = allowedVehicle.getVehicle();
            Vehicle recognisedVehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
            if (recognisedVehicle == null) {
                throw new VehicleNotFound("vehicle not valid");
            }
            //input vehicle from user is replaced with recognised vehicle got from database
            allowedVehicle.setVehicle(recognisedVehicle);


        }

    }

    @Override
    public LevelResponse deleteLevel(Level inputLevel) {
        LevelResponse levelResponse = null;
        try {
            checkLevelExist(inputLevel);
            checkLevelContainVehicles(inputLevel);
            Level level = levelDao.getLevelByLevelNumber(inputLevel.getNumber());
            levelDao.delete(level);
            levelResponse = new LevelResponse(true, "Level deleted", null);
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.info("Invalid argument while deleting level", illegalArgumentException);
            levelResponse = new LevelResponse(false, illegalArgumentException.getMessage(), null);
        } catch (Exception e) {
            logger.error("Exception occured while deleting level", e);
            levelResponse = new LevelResponse(false, "something went wrong..please try again", null);
        }


        return levelResponse;
    }
    public Boolean checkLevelContainVehicles(Level level) throws Exception {
        boolean isLevelContainsVehicle = false;
        level = levelDao.getLevelByLevelNumber(level.getNumber());
        for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
            if (allowedVehicle.getOccupiedSlots() > 0) {
                throw new IllegalAccessException("level contains vehicle");
            }
        }
        return isLevelContainsVehicle;
    }

    @Override
    public LevelResponse editLevel(Level inputLevel) {
        LevelResponse levelResponse = null;


        try {
            checkLevelExist(inputLevel);
            checkLevelContainVehicles(inputLevel);
            //Vehicles allowed for this level
            validateVehicles(inputLevel.getAllowedVehicles());
            Level level = levelDao.update(inputLevel);
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                allowedVehicle.setLevel(level);
            }
            levelDao.update(level);
            levelResponse = new LevelResponse(true, "Level edited", inputLevel);
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.info("Invalid argument while editing level", illegalArgumentException);
            levelResponse = new LevelResponse(false, illegalArgumentException.getMessage(), null);
        } catch (Exception e) {
            logger.error("error while editing level",e);
            levelResponse = new LevelResponse(false, "something went wrong..please try again", null);
        }
        return levelResponse;
    }

}
