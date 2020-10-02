package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    @Autowired
    SpaceManager spaceManager;

    @Autowired
    LevelDao levelDao;

    public ParkResponse park(Vehicle inputVehicle) {
        ParkResponse parkResponse;
        try {
           LevelVehicle levelVehicle= parkVehicle(inputVehicle);
           parkResponse = new ParkResponse(true,"vehicle parked",levelVehicle);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false,e.getMessage(),null);
        }
        return parkResponse;
    }



    private LevelVehicle parkVehicle(Vehicle inputVehicle) throws Exception {
        Vehicle vehicle= getValidVehicleType(inputVehicle.getName());
        if(vehicle==null){
            throw new Exception("This vehicle can not be parked here");
        }

        int availableLevelNumber=getAvailableLevelNumber(vehicle);
        if(availableLevelNumber<0){
            throw new Exception("Parking Space is Full");
        }

        LevelVehicle levelVehicle = levelDao.fillSlot(availableLevelNumber,vehicle.getType());
        if(levelVehicle==null){
            throw new Exception("Parking Space is Full");
        }
        return levelVehicle;
    }



    private Vehicle getValidVehicleType(String name){
        for(Vehicle validVehicle : levelDao.getVehicleList()){
            if(validVehicle.getName().equals(name)){
                return validVehicle;
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
