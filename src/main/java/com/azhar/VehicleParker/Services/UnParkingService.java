package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnParkingService {

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



    private LevelVehicle unParkVehicle(LevelVehicle inputLevelVehicle) throws Exception {
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

    private LevelVehicle getValidLevelVehicle(LevelVehicle levelVehicle) {
        //check whether the vehicle is parked or not
        for(LevelVehicle validLevelVehicle : levelDao.getLevelVehicleList()){
            if(validLevelVehicle.getId()==levelVehicle.getId()){
                return validLevelVehicle;
            }
        }
        return null;

    }



    private int getAvailableLevelNumber(Vehicle vehicle){
        int levelNo =-1;

        for(LevelSpace levelSpace:levelDao.getAvailableSpace()){
            try {
                int freeSlot = levelSpace.getAvailabeSpace().get(vehicle.getName());
                if(freeSlot>0){
                    levelNo = levelSpace.getLevelNumber();
                    return levelNo;
                }

            } catch (Exception e) {
                levelNo = -1;
            }

        }
        return levelNo;

//        for(Level level : levelDao.getLevelList()){
//            for(Vehicle vehicle1 : level.getAllowedVehicles())
//            {
//                if(vehicle.getType().equals(vehicle1.getType())){
//                    int currentSlot = vehicle1.getOccupiedSlots();
//                    int freeslots = vehicle1.getMAX_SLOTS()-currentSlot;
//                    if(freeslots>0){
//                        levelNo = level.getLevelNumber();
//                        break;
//                    }
//
//                }
//            }
//
//        }


    }


}
