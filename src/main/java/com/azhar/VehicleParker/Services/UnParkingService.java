package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnParkingService implements com.azhar.VehicleParker.Services.Interfaces.UnParkingService {

    @Autowired
    LevelDao levelDao;

    public ParkResponse unPark(LevelVehicle inputLevelVehicle) {
        ParkResponse parkResponse;
        try {
           LevelVehicle levelVehicleResponse= unParkVehicle(inputLevelVehicle);
           parkResponse = new ParkResponse(true,"vehicle unparked",levelVehicleResponse);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false,e.getMessage(),null);
        }
        return parkResponse;
    }



    public LevelVehicle unParkVehicle(LevelVehicle inputLevelVehicle) throws Exception {
        LevelVehicle levelVehicle= getValidLevelVehicle(inputLevelVehicle);
        if(levelVehicle==null){
            //not levelvehicle exist with the id entered by user
            throw new Exception("This vehicle is not parked here");
        }

        boolean isSlotEmptied = levelDao.emptySlot(levelVehicle);
        if(!isSlotEmptied){
            throw new Exception("Some error occured.Please try again");
        }
        return levelVehicle;
    }

    public LevelVehicle getValidLevelVehicle(LevelVehicle levelVehicle) {
        //check whether the vehicle is parked or not
        for(LevelVehicle validLevelVehicle : levelDao.getLevelVehicleList()){
            if(validLevelVehicle.getId()==levelVehicle.getId()){
                return validLevelVehicle;
            }
        }
        return null;

    }


}
