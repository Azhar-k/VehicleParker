package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnParkingService implements com.azhar.VehicleParker.Services.UnParkingService {

    @Autowired
    LevelDao levelDao;
    @Autowired
    AllowedVehicleDao allowedVehicleDao;
    @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;

    public ParkResponse unPark(LevelParkedVehicle inputLevelParkedVehicle) {
        ParkResponse parkResponse;
        try {
            LevelParkedVehicle levelParkedVehicleResponse = unParkVehicle(inputLevelParkedVehicle);
            parkResponse = new ParkResponse(true, "vehicle unparked", levelParkedVehicleResponse);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false, e.getMessage(), null);
        }
        return parkResponse;
    }


    public LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelParkedVehicle) throws Exception {
        LevelParkedVehicle levelParkedVehicle = getValidLevelParkedVehicle(inputLevelParkedVehicle.getId());
        if (levelParkedVehicle == null) {
            //no levelAllowedvehicle exist with the id entered by user
            throw new Exception("This vehicle is not parked here");
        }

        boolean isSlotEmptied = emptySlot(levelParkedVehicle);
        if (!isSlotEmptied) {
            throw new Exception("Some error occured.Please try again");
        }
        return levelParkedVehicle;
    }

    public LevelParkedVehicle getValidLevelParkedVehicle(int levelParkedVehicleId) {
        //find out the parked vehicle
        LevelParkedVehicle levelParkedVehicle = levelParkedVehicleDao.getLevelParkedVehicleById(levelParkedVehicleId);
        return levelParkedVehicle;

    }

    public Boolean emptySlot(LevelParkedVehicle levelParkedVehicle) {
        boolean isLevelAllowedVehicleRemoved = removeLevelAllowedVehicle(levelParkedVehicle);
        if (isLevelAllowedVehicleRemoved) {
            int levelNumber = levelParkedVehicle.getLevelNumber();
            int parkedVehicleId = levelParkedVehicle.getVehicleType();

            Level level = levelDao.getLevelByLevelNumber(levelNumber);
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                if (allowedVehicle.getVehicle().getId() == parkedVehicleId) {

                    int currentOccupiedSlot = allowedVehicle.getOccupiedSlots();
                    int updatedOccupiedSlot = currentOccupiedSlot - 1;
                    allowedVehicle.setOccupiedSlots(updatedOccupiedSlot);

                    allowedVehicleDao.update(allowedVehicle);
                }

            }
            levelDao.update(level);

        }
        return true;

    }

    public boolean removeLevelAllowedVehicle(LevelParkedVehicle levelParkedVehicle) {
        levelParkedVehicleDao.delete(levelParkedVehicle);
        return true;
    }


}
