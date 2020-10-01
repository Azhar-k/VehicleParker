package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Car;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    SpaceManager spaceManager;

    @Autowired
    LevelDao levelDao;

    public ParkResponse park(Vehicle vehicle) {
        ParkResponse parkResponse;
        try {
           LevelVehicleMap levelVehicleMap= parkVehicle(vehicle);
           parkResponse = new ParkResponse(true,"vehicle parked",levelVehicleMap);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false,e.getMessage(),null);
        }
        return parkResponse;
    }



    private LevelVehicleMap parkVehicle(Vehicle vehicle) throws Exception {
        boolean isVehicleTypeValid = validateVehicleType(vehicle);
        if(!isVehicleTypeValid){
            throw new Exception("This vehicle can not be parked here");
        }

        int availableLevelNumber=getAvailableLevelNumber(vehicle);
        if(availableLevelNumber<0){
            throw new Exception("Parking Space is Full");
        }

        return new LevelVehicleMap(availableLevelNumber,vehicle.getId());
    }

    private boolean validateVehicleType(Vehicle vehicle){
        if(vehicle==null){
            return false;
        }
        return true;
    }

    private int getAvailableLevelNumber(Vehicle vehicle){
        int levelNo =-1;

        for(Level level : levelDao.getLevelList()){

            if(level.getLevelCapacity().getFreeSlots(vehicle)>0){
                levelNo = level.getLevelNumber();
                break;
            }
        }
        return levelNo;
    }


}
