package com.azhar.VehicleParker.Services.Implimentations;


import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicleMap;
import com.azhar.VehicleParker.Entities.Responses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService implements com.azhar.VehicleParker.Services.Interfaces.ParkingService {

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

        String type= vehicle.getType();
        int availableLevelNumber=getAvailableLevelNumber(type);
        if(availableLevelNumber<0){
            throw new Exception("Parking Space is Full");
        }

        LevelVehicleMap levelVehicleMap = new LevelVehicleMap(availableLevelNumber,vehicle.getType());
        int levelVehicleMapid = levelDao.fillSlot(levelVehicleMap);
        if(levelVehicleMapid==-1){
            throw new Exception("Database error . Please try again");
        }
        levelVehicleMap.setId(levelVehicleMapid);
        return levelVehicleMap;
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

        List<LevelSpace> levelSpaceList = levelDao.getAvailableSpace();
        int levelNo =-1;

        for(LevelSpace levelSpace : levelSpaceList){

            switch (type){
                case "car":
                    if(levelSpace.getAvailableCarSpace()>=0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                case "bus":
                    if(levelSpace.getAvailableBusSpace()>=0){
                        System.out.println("level :"+levelSpace.getLevelNumber());
                        System.out.println("bus space : "+levelSpace.getAvailableBusSpace());
                        levelNo=levelSpace.getLevelNumber();
                        return levelNo;
                    }
                    break;
                case "bike":
                    if(levelSpace.getAvailableBikeSpace()>=0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                case "van":
                    if(levelSpace.getAvailableVanSpace()>=0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                default:
                    levelNo=-1;
            }
           if(levelNo>-1){
               break;
           }


        }
        System.out.println("returned level :"+levelNo);
        return levelNo;
    }

}
