package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.ApiResponses.ParkResponse;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import com.azhar.VehicleParker.Services.SpaceManager;
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

    public ParkResponse park(ParkRequest parkRequest ) {
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
        Vehicle vehicle = getVehicle(parkRequest.getVehicleName());
        if (vehicle == null) {
            throw new Exception("This vehicle can not be parked here");
        }

        //try to find out a free level to park the vehicle
        int availableLevelNumber = getAvailableLevelNumber(vehicle);
        if (availableLevelNumber < 0) {

            throw new Exception("Parking Space is Full for " + vehicle.getName());
        }
        //close the slot in database and get a unique id for this parking
        LevelParkedVehicle levelParkedVehicle = fillSlot(availableLevelNumber, vehicle.getId());
        if (levelParkedVehicle == null) {
            throw new Exception("some error occured");
        }
        return levelParkedVehicle;
    }


    public Vehicle getVehicle(String name) {
        Vehicle vehicle = vehicleDao.getVehicleByName(name);
        return vehicle;
    }

    public int getAvailableLevelNumber(Vehicle vehicle) {
        int levelNo = -1;

        for (LevelSpace levelSpace : spaceManager.getLAvailableSpace()) {
            //index of availabeSpace list is the level number
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

    public LevelParkedVehicle fillSlot(int levelNumber, int parkedVehicleId) {

        LevelParkedVehicle levelAllowedVehicle = addLevelAllowedVehicle(levelNumber, parkedVehicleId);
        if (levelAllowedVehicle != null) {
            Level level = levelDao.getLevelByLevelNumber(levelNumber);

            for(AllowedVehicle allowedVehicle:level.getAllowedVehicles()) {
                if (allowedVehicle.getVehicle().getId() == parkedVehicleId) {

                    int currentOccupiedSlot = allowedVehicle.getOccupiedSlots();
                    int updatedOccupiedSlot = currentOccupiedSlot + 1;
                    allowedVehicle.setOccupiedSlots(updatedOccupiedSlot);

                    allowedVehicleDao.update(allowedVehicle);
                }

            }

            levelDao.update(level);


        }
        return levelAllowedVehicle;

    }

    public LevelParkedVehicle addLevelAllowedVehicle(int levelNumber, int parkedVehicleId) {
        LevelParkedVehicle levelParkedVehicle = null;

        try {

            levelParkedVehicle = new LevelParkedVehicle(levelNumber, parkedVehicleId);
            levelParkedVehicleDao.insert(levelParkedVehicle);
        } catch (Exception e) {
            levelParkedVehicle = null;
        }

        return levelParkedVehicle;
    }


}
