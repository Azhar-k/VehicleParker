package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
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
           LevelVehicleMap levelSpace= parkVehicle(vehicle);
           parkResponse = new ParkResponse(true,"vehicle parked",levelSpace);

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

        String type= vehicle.getType();
        int availableLevelNumber=getAvailableLevelNumber(type);
        if(availableLevelNumber<=0){
            throw new Exception("Parking Space is Full");
        }

        return new LevelVehicleMap(1,vehicle.getType());
    }

    private boolean validateVehicleType(Vehicle vehicle){
        List<Vehicle> availableVehicleList = levelDao.getVehicleList();
        for(Vehicle availableVehicle:availableVehicleList){
            if(availableVehicle.getType().equals(vehicle.getType())){
                return true;
            }
        }
        return false;
    }

    private int getAvailableLevelNumber(String type){

        List<LevelSpace> levelSpaceList = spaceManager.getLAvailableSpace();
        int levelNo =-1;
        for(LevelSpace levelSpace : levelSpaceList){
            switch (type){
                case "car":
                    if(levelSpace.getAvailableCarSpace()>0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                case "bus":
                    if(levelSpace.getAvailableBusSpace()>0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                case "bike":
                    if(levelSpace.getAvailableBikeSpace()>0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                case "van":
                    if(levelSpace.getAvailableVanSpace()>0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                default:
                    levelNo=-1;
            }
        }
        return levelNo;
    }
}
