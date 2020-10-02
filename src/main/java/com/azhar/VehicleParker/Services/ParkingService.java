package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService implements com.azhar.VehicleParker.Services.Interfaces.ParkingService {

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



    public LevelVehicle parkVehicle(Vehicle inputVehicle) throws Exception {
        Vehicle vehicle= getValidVehicleType(inputVehicle.getName());
        if(vehicle==null){
            throw new Exception("This vehicle can not be parked here");
        }

        int availableLevelNumber=getAvailableLevelNumber(vehicle);
        if(availableLevelNumber<0){
            throw new Exception("Parking Space is Full for "+vehicle.getName());
        }

        LevelVehicle levelVehicle = levelDao.fillSlot(availableLevelNumber,vehicle.getType());
        if(levelVehicle==null){
            throw new Exception("Parking Space is Full");
        }
        return levelVehicle;
    }



    public Vehicle getValidVehicleType(String name){
        for(Vehicle validVehicle : levelDao.getVehicleList()){

            if(validVehicle.getName().equals(name)){

                return validVehicle;
            }
        }
        return null;
    }

    public int getAvailableLevelNumber(Vehicle vehicle){
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
    }


}
