package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnParkingService implements com.azhar.VehicleParker.Services.Interfaces.UnParkingService {

    @Autowired
    LevelDao levelDao;

    public ParkResponse unPark(LevelParkedVehicle inputLevelParkedVehicle) {
        ParkResponse parkResponse;
        try {
           LevelParkedVehicle levelParkedVehicleResponse= unParkVehicle(inputLevelParkedVehicle);
           parkResponse = new ParkResponse(true,"vehicle unparked",levelParkedVehicleResponse);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false,e.getMessage(),null);
        }
        return parkResponse;
    }



    public LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelParkedVehicle) throws Exception {
        LevelParkedVehicle levelParkedVehicle= getValidLevelParkedVehicle(inputLevelParkedVehicle.getId());
        if(levelParkedVehicle==null){
            //no levelvehicle exist with the id entered by user
            throw new Exception("This vehicle is not parked here");
        }

        boolean isSlotEmptied = levelDao.emptySlot(levelParkedVehicle);
        if(!isSlotEmptied){
            throw new Exception("Some error occured.Please try again");
        }
        return levelParkedVehicle;
    }

    public LevelParkedVehicle getValidLevelParkedVehicle(int levelParkedVehicleId) {
        //check whether the vehicle is parked or not
        for(LevelParkedVehicle validLevelParkedVehicle : levelDao.getLevelParkedVehicleList()){
            if(validLevelParkedVehicle.getId()==levelParkedVehicleId){
                return validLevelParkedVehicle;
            }
        }
        return null;

    }


}
