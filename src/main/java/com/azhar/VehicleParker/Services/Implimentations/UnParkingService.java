package com.azhar.VehicleParker.Services.Implimentations;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnParkingService implements com.azhar.VehicleParker.Services.Interfaces.UnParkingService {


    @Autowired
    LevelDao levelDao;

    public ParkResponse unpark(LevelVehicleMap levelVehicleMap) {
        ParkResponse parkResponse;
        try {
            LevelVehicleMap levelVehicleMapResponse= unparkVehicle(levelVehicleMap);
            parkResponse = new ParkResponse(true,"vehicle unparked",levelVehicleMapResponse);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false,e.getMessage(),null);
        }
        return parkResponse;

    }

    private LevelVehicleMap unparkVehicle(LevelVehicleMap levelVehicleMap) throws Exception {
        boolean isVehicleExist = isVehicleExist(levelVehicleMap);

        if(!isVehicleExist){
            throw new Exception("Your vehicle is not parked. Please recheck id");
        }
        boolean isSlotEmptied = levelDao.emptySlot(levelVehicleMap);

        if(!isSlotEmptied){
            throw new Exception("Database error occure. Please try again");
        }
        return levelVehicleMap;
    }

    private boolean isVehicleExist(LevelVehicleMap levelVehicleMap) {
        return levelDao.isLevelVehiclMapIdExist(levelVehicleMap.getId());
    }
}
