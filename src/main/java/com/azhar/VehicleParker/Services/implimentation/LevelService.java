package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.EditLevelResponse;
import com.azhar.VehicleParker.db.entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.entities.Building.Level;
import com.azhar.VehicleParker.db.entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelService implements com.azhar.VehicleParker.Services.LevelService {

    @Autowired
    LevelDao levelDao;
    @Autowired
    AllowedVehicleDao allowedVehicleDao;
    @Autowired
    VehicleDao vehicleDao;

    @Override
    public EditLevelResponse insertLevel(Level inputLevel) {
        EditLevelResponse editLevelResponse = null;

        if (!isLevelExist(inputLevel)) {
            try {
                for (AllowedVehicle allowedVehicle : inputLevel.getAllowedVehicles()) {
                    Vehicle inputVehicle = allowedVehicle.getVehicle();
                    Vehicle vehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
                    System.out.println("at here:" + vehicle.getId());
                    allowedVehicle.setVehicle(vehicle);
                    allowedVehicleDao.insert(allowedVehicle);
                }
                Level level = levelDao.insert(inputLevel);
                editLevelResponse = new EditLevelResponse(true, "Level added", level);

            } catch (Exception e) {
                String errorMessage = e.getMessage();
                editLevelResponse = new EditLevelResponse(false, errorMessage, null);
            }

        } else {
            String errorMessage = "level already exist";
            editLevelResponse = new EditLevelResponse(false, errorMessage, null);
        }

        return editLevelResponse;

    }


    @Override
    public EditLevelResponse deleteLevel(Level inputLevel) {
        EditLevelResponse editLevelResponse = null;

        boolean isLevelExist = isLevelExist(inputLevel);

        if (isLevelExist) {
            if (!isLevelContainVehicles(inputLevel)) {
                try {
                    Level level = levelDao.getLevelByLevelNumber(inputLevel.getLevelNumber());
                    levelDao.delete(level);
                    editLevelResponse = new EditLevelResponse(true, "Level deleted", null);
                } catch (Exception e) {
                    editLevelResponse = new EditLevelResponse(false, "something went wrong..please try again", null);
                }
            } else {
                editLevelResponse = new EditLevelResponse(false, "Level can not be deleted. It contains vehicle", null);
            }

        } else {
            editLevelResponse = new EditLevelResponse(false, "input Level does not exist", null);
        }
        return editLevelResponse;
    }

    @Override
    public EditLevelResponse editLevel(Level inputLevel) {
        EditLevelResponse editLevelResponse = null;

        boolean isLevelExist = isLevelExist(inputLevel);

        if (isLevelExist) {
            if (!isLevelContainVehicles(inputLevel)) {
                try {
                    for (AllowedVehicle allowedVehicle : inputLevel.getAllowedVehicles()) {
                        Vehicle inputVehicle = allowedVehicle.getVehicle();
                        Vehicle validVehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
                        allowedVehicle.setVehicle(validVehicle);
                        allowedVehicleDao.update(allowedVehicle);
                    }
                    levelDao.update(inputLevel);
                    editLevelResponse = new EditLevelResponse(true, "Level edited", inputLevel);
                } catch (Exception e) {
                    editLevelResponse = new EditLevelResponse(false, "something went wrong..please try again" + e.getMessage(), null);
                }
            } else {
                editLevelResponse = new EditLevelResponse(false, "Level can not be edited. It contains vehicle", null);
            }

        } else {
            editLevelResponse = new EditLevelResponse(false, "input Level does not exist", null);
        }
        return editLevelResponse;
    }

    @Override
    public Boolean isLevelExist(Level inputLevel) {
        boolean isLevelExist = true;
        Level level = levelDao.getLevelByLevelNumber(inputLevel.getLevelNumber());
        if (level == null) {
            isLevelExist = false;
        }
        return isLevelExist;
    }

    @Override
    public Boolean isLevelContainVehicles(Level level) {
        boolean isLevelContainsVehicle = false;
        level = levelDao.getLevelByLevelNumber(level.getLevelNumber());
        for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
            if (allowedVehicle.getOccupiedSlots() > 0) {
                isLevelContainsVehicle = true;
            }
        }
        return isLevelContainsVehicle;
    }
}
