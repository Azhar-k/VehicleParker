package com.azhar.VehicleParker.Services.Implimentations;


import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Level.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
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
            //try to park a vehicle
           LevelVehicle levelVehicle= parkVehicle(vehicle);//retrieve the parked instance
            //format the api response
           parkResponse = new ParkResponse(true,"vehicle parked",levelVehicle);

        } catch (Exception e) {
            //parking failed due to some reason
            parkResponse = new ParkResponse(false,e.getMessage(),null);
        }
        return parkResponse;
    }

    private LevelVehicle parkVehicle(Vehicle vehicle) throws Exception {

        boolean isVehicleTypeValid = validateVehicleType(vehicle);
        if(!isVehicleTypeValid){
            throw new Exception("This vehicle can not be parked here"); //
        }

        String type= vehicle.getType();
        //try to find out a free level to park the vehicle
        int availableLevelNumber=getAvailableLevelNumber(type);
        if(availableLevelNumber<0){
            //all level are filled for this vehicle type
            throw new Exception("Parking Space is Full");
        }

        LevelVehicle levelVehicleMap = new LevelVehicle(availableLevelNumber,vehicle.getType());
        int levelVehicleMapid;
        try{
            levelVehicleMapid = levelDao.fillSlot(levelVehicleMap);//close the slot in database and get a unique id for this parking

        } catch (Exception e) {
            throw new Exception("database error occured");
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

        int levelNo =-1;//no level is available

        //levelSpace instance represent a level and it contain the information regarding available freespace of each vehicle type
        for(LevelSpace levelSpace : levelSpaceList){

            switch (type){
                case "car":
                    if(levelSpace.getAvailableCarSpace()>0){
                        levelNo=levelSpace.getLevelNumber();
                    }
                    break;
                case "bus":
                    if(levelSpace.getAvailableBusSpace()>0){
                        System.out.println("level :"+levelSpace.getLevelNumber());
                        System.out.println("bus space : "+levelSpace.getAvailableBusSpace());
                        levelNo=levelSpace.getLevelNumber();
                        return levelNo;
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
           if(levelNo>-1){
               //a level hasbeen found.so stop iteration
               break;
           }


        }

        return levelNo;
    }

}
