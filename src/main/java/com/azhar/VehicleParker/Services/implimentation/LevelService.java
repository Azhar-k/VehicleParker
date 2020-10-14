package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService implements com.azhar.VehicleParker.Services.LevelService {

    @Autowired
    LevelDao levelDao;
    @Autowired
    AllowedVehicleDao allowedVehicleDao;
    @Autowired
    VehicleDao vehicleDao;

    @Override
    public LevelResponse insertLevel(Level inputLevel) {
        LevelResponse editLevelResponse = null;

        if (!isLevelExist(inputLevel)) {
            try {
                //Vehicles allowed for this level is inserted into database
                boolean isAllowedVehiclesInserted = insertAllowedVehicles(inputLevel.getAllowedVehicles());
                if(isAllowedVehiclesInserted){
                    Level level = levelDao.insert(inputLevel);
                    editLevelResponse = new LevelResponse(true, "Level added", level);
                }

            } catch (Exception e) {
                String errorMessage = e.getMessage();
                editLevelResponse = new LevelResponse(false, errorMessage, null);
            }

        } else {
            String errorMessage = "level already exist";
            editLevelResponse = new LevelResponse(false, errorMessage, null);
        }

        return editLevelResponse;

    }

    private boolean insertAllowedVehicles(List<AllowedVehicle> allowedVehicles) throws Exception {
        for (AllowedVehicle allowedVehicle : allowedVehicles) {
            try {
                Vehicle inputVehicle = allowedVehicle.getVehicle();
                //input vehicle should be validated before editing.User may try to add non recognised vehicle
                Vehicle recognisedVehicle = vehicleDao.getVehicleByName(inputVehicle.getName());
                //input vehicle from user is replaced with recognised vehicle got from database
                allowedVehicle.setVehicle(recognisedVehicle);
                allowedVehicleDao.update(allowedVehicle);
            } catch (Exception e) {
                throw e;
            }
           
        }
        return true;
    }


    @Override
    public LevelResponse deleteLevel(Level inputLevel) {
        LevelResponse editLevelResponse = null;

        boolean isLevelExist = isLevelExist(inputLevel);

        if (isLevelExist) {
            if (!isLevelContainVehicles(inputLevel)) {
                try {
                    Level level = levelDao.getLevelByLevelNumber(inputLevel.getNumber());
                    removeAllowedVehicleMapping(level);
                    levelDao.delete(level);
                    editLevelResponse = new LevelResponse(true, "Level deleted", null);
                } catch (Exception e) {
                    editLevelResponse = new LevelResponse(false, "something went wrong..please try again", null);
                }
            } else {
                editLevelResponse = new LevelResponse(false, "Level can not be deleted. It contains vehicle", null);
            }

        } else {
            editLevelResponse = new LevelResponse(false, "input Level does not exist", null);
        }
        return editLevelResponse;
    }

    private void removeAllowedVehicleMapping(Level level) throws Exception {
        try{
            for(AllowedVehicle allowedVehicle:level.getAllowedVehicles()){
                allowedVehicleDao.delete(allowedVehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public LevelResponse editLevel(Level inputLevel) {
        LevelResponse editLevelResponse = null;

        boolean isLevelExist = isLevelExist(inputLevel);

        if (isLevelExist) {
            if (!isLevelContainVehicles(inputLevel)) {
                try {
                    //Vehicles allowed for this level is inserted into database
                    boolean isAllowedVehiclesInserted = insertAllowedVehicles(inputLevel.getAllowedVehicles());
                    if(isAllowedVehiclesInserted){
                        levelDao.update(inputLevel);
                        editLevelResponse = new LevelResponse(true, "Level edited", inputLevel);
                    }
                    
                } catch (Exception e) {
                    editLevelResponse = new LevelResponse(false, "something went wrong..please try again" + e.getMessage(), null);
                }
            } else {
                editLevelResponse = new LevelResponse(false, "Level can not be edited. It contains vehicle", null);
            }

        } else {
            editLevelResponse = new LevelResponse(false, "input Level does not exist", null);
        }
        return editLevelResponse;
    }

    @Override
    public Boolean isLevelExist(Level inputLevel) {
        boolean isLevelExist = true;
        Level level = levelDao.getLevelByLevelNumber(inputLevel.getNumber());
        if (level == null) {
            isLevelExist = false;
        }
        return isLevelExist;
    }

    @Override
    public Boolean isLevelContainVehicles(Level level) {
        boolean isLevelContainsVehicle = false;
        level = levelDao.getLevelByLevelNumber(level.getNumber());
        for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
            if (allowedVehicle.getOccupiedSlots() > 0) {
                isLevelContainsVehicle = true;
            }
        }
        return isLevelContainsVehicle;
    }
}
