package com.azhar.VehicleParker.UnitTests.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import com.azhar.VehicleParker.UnitTests.Services.SpaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService implements com.azhar.VehicleParker.UnitTests.Services.ParkingService {

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
        ////Create a levelParkedVehicle for this parking get a unique id for this parking.Unique id is attribute of LevelParkedVehicle
        LevelParkedVehicle levelParkedVehicle = addLevelParkedVehicle(availableLevelNumber, vehicle.getId(), vehicle.getName(), parkRequest.getVehicleNumber());
        if(levelParkedVehicle==null){
            throw new Exception("This vehicle is already parked ");
        }

        //close the slot in the level corresponding to the parking
        boolean isSlotFilled = fillSlot(availableLevelNumber, vehicle.getId(), vehicle.getName(), parkRequest.getVehicleNumber());

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

    public boolean fillSlot(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber) {
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

    public LevelParkedVehicle addLevelParkedVehicle(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber) {
        LevelParkedVehicle levelParkedVehicle = null;

        try {

            levelParkedVehicle = new LevelParkedVehicle(levelNumber, parkedVehicleId, vehicleName, vehicleNumber);
            levelParkedVehicleDao.insert(levelParkedVehicle);
        } catch (Exception e) {
            levelParkedVehicle = null;
        }

        return levelParkedVehicle;
    }


}
