package com.azhar.VehicleParker.Services.Implimentations;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnParkingService implements com.azhar.VehicleParker.Services.Interfaces.UnParkingService {


    @Autowired
    LevelDao levelDao;

    public ParkResponse unpark(LevelVehicle levelVehicleMap) {

        ParkResponse parkResponse;
        try {
            //try to unpark the instance
            LevelVehicle levelVehicleMapResponse = unparkVehicle(levelVehicleMap);
            //format api response
            parkResponse = new ParkResponse(true, "vehicle unparked", levelVehicleMapResponse);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false, e.getMessage(), null);
        }
        return parkResponse;

    }

    private LevelVehicle unparkVehicle(LevelVehicle levelVehicleMap) throws Exception {
        boolean isVehicleExist = isVehicleExist(levelVehicleMap);

        if (!isVehicleExist) {
            throw new Exception("Your vehicle is not parked. Please recheck id");
        }
        boolean isSlotEmptied = levelDao.emptySlot(levelVehicleMap);//unpark the vehicle from database
        if (!isSlotEmptied) {
            throw new Exception("Database error occure. Please try again");
        }
        return levelVehicleMap;
    }

    private boolean isVehicleExist(LevelVehicle levelVehicleMap) {
        //check whether the vehicle is parked or not
        return levelDao.isLevelVehiclMapIdExist(levelVehicleMap.getId());
    }
}
