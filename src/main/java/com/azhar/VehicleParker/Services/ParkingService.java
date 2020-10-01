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

        List<LevelSpace> levelSpaceList = spaceManager.getLAvailableSpace();
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
            System.out.println(".......");


        }
        System.out.println("returned level :"+levelNo);
        return levelNo;
    }

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
