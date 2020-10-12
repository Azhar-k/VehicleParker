package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Services.SpaceManager;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService implements com.azhar.VehicleParker.Services.ParkingService {

    @Autowired
    LevelDao levelDao;
    @Autowired
    AllowedVehicleDao allowedVehicleDao;
    @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;
    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    SpaceManager spaceManager;

    public ParkResponse park(ParkRequest parkRequest) {
        ParkResponse parkResponse;
        try {
            //try to park a vehicle
            LevelParkedVehicle levelParkedVehicle = parkVehicle(parkRequest);
            //format the api response
            parkResponse = new ParkResponse(true, "vehicle parked", levelParkedVehicle);

        } catch (Exception e) {
            //parking failed due to some reason
            parkResponse = new ParkResponse(false, e.getMessage(), null);
        }
        return parkResponse;
    }


    public LevelParkedVehicle parkVehicle(ParkRequest parkRequest) throws Exception {

        //validate the vehicle given by user
        Vehicle vehicle = getVehicleByName(parkRequest.getVehicleName());
        if (vehicle == null) {
            throw new Exception("This vehicle can not be parked here");
        }

        //try to find out a free level to park the vehicle
        int availableLevelNumber = getAvailableLevelNumber(vehicle);
        if (availableLevelNumber < 0) {

            throw new Exception("Parking Space is Full for " + vehicle.getName());
        }
        //Create a levelParkedVehicle for this parking get a unique id for this parking.Unique id is attribute of LevelParkedVehicle
        LevelParkedVehicle levelParkedVehicle = addLevelParkedVehicle(availableLevelNumber, vehicle.getId(), vehicle.getName(), parkRequest.getVehicleNumber(),vehicle.getParkingRate());
        if(levelParkedVehicle==null){
            throw new Exception("This vehicle is already parked ");
        }

        //close the slot in the level corresponding to the parking
        boolean isSlotFilled = fillSlot(availableLevelNumber, vehicle.getId());

        if (isSlotFilled==false) {
            throw new Exception("some error occured..Please try again");
        }
        return levelParkedVehicle;
    }


    public Vehicle getVehicleByName(String name) {
        Vehicle vehicle = vehicleDao.getVehicleByName(name);
        return vehicle;
    }

    public int getAvailableLevelNumber(Vehicle vehicle) {
        //-1 indicate no slot available
        int levelNo = -1;
        for (LevelSpace levelSpace : spaceManager.getLAvailableSpace()) {
            try {
                int freeSlot = levelSpace.getAvailabeSlots().get(vehicle.getName());
                if (freeSlot > 0) {
                    levelNo = levelSpace.getLevelNumber();
                    return levelNo;
                }

            } catch (Exception e) {
                levelNo = -1;
            }

        }
        return levelNo;
    }

    public LevelParkedVehicle addLevelParkedVehicle(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber,int parkingRate) {
        LevelParkedVehicle levelParkedVehicle = null;

        try {

            levelParkedVehicle = new LevelParkedVehicle(levelNumber, parkedVehicleId, vehicleName, vehicleNumber,parkingRate);
            levelParkedVehicleDao.insert(levelParkedVehicle);
        } catch (Exception e) {
            levelParkedVehicle = null;
        }

        return levelParkedVehicle;
    }

    public boolean fillSlot(int levelNumber, int parkedVehicleId) {
        boolean isSlotFilled = false;
        try{
            Level level = levelDao.getLevelByLevelNumber(levelNumber);

            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                if (allowedVehicle.getVehicle().getId() == parkedVehicleId) {

                    int currentOccupiedSlot = allowedVehicle.getOccupiedSlots();
                    int updatedOccupiedSlot = currentOccupiedSlot + 1;
                    allowedVehicle.setOccupiedSlots(updatedOccupiedSlot);

                    allowedVehicleDao.update(allowedVehicle);
                }

            }

            levelDao.update(level);
            isSlotFilled=true;

        } catch (Exception e) {

        }
        return isSlotFilled;

    }

    public ParkResponse unPark(LevelParkedVehicle inputLevelParkedVehicle) {
        ParkResponse parkResponse;
        try {
            LevelParkedVehicle levelParkedVehicleResponse = unParkVehicle(inputLevelParkedVehicle);
            parkResponse = new ParkResponse(true, "vehicle unparked", levelParkedVehicleResponse);

        } catch (Exception e) {
            parkResponse = new ParkResponse(false, e.getMessage(), null);
        }
        return parkResponse;
    }


    public LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelParkedVehicle) throws Exception {
        LevelParkedVehicle levelParkedVehicle = getValidLevelParkedVehicle(inputLevelParkedVehicle.getId());
        if (levelParkedVehicle == null) {
            //no levelAllowedvehicle exist with the id entered by user
            throw new Exception("This vehicle is not parked here");
        }

        //remove the LevelParkedVehicle for this parking
        boolean isLevelParkedVehicleRemoved = removeLevelParkedVehicle(levelParkedVehicle);
        //Empty the slot in the level
        boolean isSlotEmptied = emptySlot(levelParkedVehicle);

        boolean isDatabaseOperationsSuccess = isSlotEmptied&&isLevelParkedVehicleRemoved;
        if (!isDatabaseOperationsSuccess ) {
            throw new Exception("Some error occured.Please try again");
        }
        return levelParkedVehicle;
    }

    public LevelParkedVehicle getValidLevelParkedVehicle(int levelParkedVehicleId) {
        //find out the parked vehicle
        LevelParkedVehicle levelParkedVehicle = levelParkedVehicleDao.getLevelParkedVehicleById(levelParkedVehicleId);
        return levelParkedVehicle;

    }

    public Boolean emptySlot(LevelParkedVehicle levelParkedVehicle) {
        boolean isSlotEmptied = false;
        try {
            int levelNumber = levelParkedVehicle.getLevelNumber();
            int parkedVehicleId = levelParkedVehicle.getVehicleType();

            //find out level in which vehicle parked
            Level level = levelDao.getLevelByLevelNumber(levelNumber);

            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                if (allowedVehicle.getVehicle().getId() == parkedVehicleId) {

                    int currentOccupiedSlot = allowedVehicle.getOccupiedSlots();
                    int updatedOccupiedSlot = currentOccupiedSlot - 1;
                    allowedVehicle.setOccupiedSlots(updatedOccupiedSlot);

                    allowedVehicleDao.update(allowedVehicle);
                }
            }
            levelDao.update(level);
            isSlotEmptied = true;
        } catch (Exception e) {

        }
        return isSlotEmptied;
    }

    public boolean removeLevelParkedVehicle(LevelParkedVehicle levelParkedVehicle) {
        try {
            levelParkedVehicleDao.delete(levelParkedVehicle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
