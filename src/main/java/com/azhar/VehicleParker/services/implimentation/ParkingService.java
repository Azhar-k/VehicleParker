package com.azhar.VehicleParker.services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.Entities.ApiRequests.ParkRequest;
import com.azhar.VehicleParker.Entities.Exceptions.InvalidInputException;
import com.azhar.VehicleParker.Entities.Exceptions.ParkingException;
import com.azhar.VehicleParker.services.SpaceManager;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ParkingService implements com.azhar.VehicleParker.services.ParkingService {

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

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @Override
    public LevelParkedVehicle parkVehicle(ParkRequest parkRequest) throws ParkingException {

        LevelParkedVehicle levelParkedVehicle = null;
        try {
            Vehicle vehicle = getVehicleByName(parkRequest.getVehicleName());
            if (vehicle == null) {
                throw new InvalidInputException("this vehicle can not be parked here");
            }
            //try to find out a free level to park the vehicle
            int availableLevelNumber = getAvailableLevelNumber(vehicle);
            //Create a levelParkedVehicle for this parking and get a unique id for this parking.Unique id is attribute of LevelParkedVehicle
            levelParkedVehicle = addLevelParkedVehicle(availableLevelNumber, vehicle.getId(), vehicle.getName(), parkRequest.getVehicleNumber(), vehicle.getParkingRate());
            fillSlot(availableLevelNumber, vehicle.getId());
            logger.info("Vehicle parked "+levelParkedVehicle);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input received while parking vehicle", invalidInputException);
            throw new ParkingException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("error while parking vehicle", e);
            throw new ParkingException(e.getMessage(), e);
        }

        return levelParkedVehicle;
    }


    public Vehicle getVehicleByName(String name) {
        Vehicle vehicle = vehicleDao.getVehicleByName(name);
        return vehicle;
    }

    public int getAvailableLevelNumber(Vehicle vehicle) throws InvalidInputException {
        //-1 indicate no slot available
        int levelNo = -1;
        for (LevelSpace levelSpace : spaceManager.getLAvailableSpace()) {
            if (levelSpace.getAvailabeSlots().keySet().contains(vehicle.getName())) {
                int freeSlot = levelSpace.getAvailabeSlots().get(vehicle.getName());
                if (freeSlot > 0) {
                    levelNo = levelSpace.getLevelNumber();
                    break;
                }
            }
        }
        if (levelNo < 0) {
            throw new InvalidInputException("Parking Space is Full for " + vehicle.getName());
        }
        return levelNo;
    }

    public LevelParkedVehicle addLevelParkedVehicle(int levelNumber, int parkedVehicleId, String vehicleName, String vehicleNumber, int parkingRate) throws Exception {
        LevelParkedVehicle levelParkedVehicle = null;
        levelParkedVehicle = new LevelParkedVehicle(levelNumber, parkedVehicleId, vehicleName, vehicleNumber, parkingRate);
        try{
            levelParkedVehicleDao.insert(levelParkedVehicle);
        } catch (Exception e) {
            throw new InvalidInputException("This vehicle is already parked");
        }

        return levelParkedVehicle;
    }

    public boolean fillSlot(int levelNumber, int parkedVehicleId) {
        boolean isSlotFilled = true;
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
        return isSlotFilled;
    }


    @Override
    public LevelParkedVehicle unParkVehicle(LevelParkedVehicle inputLevelParkedVehicle) throws ParkingException {
        LevelParkedVehicle levelParkedVehicle = null;
        try {
            levelParkedVehicle = getValidLevelParkedVehicle(inputLevelParkedVehicle.getId());
            if (levelParkedVehicle == null) {
                //no levelParkedvehicle exist with the id entered by user
                throw new InvalidInputException("This vehicle is not parked here");
            }
            removeLevelParkedVehicle(levelParkedVehicle);
            emptySlot(levelParkedVehicle);
            logger.info("Vehicle unparked "+levelParkedVehicle);
        } catch (InvalidInputException invalidInputException) {
            logger.error("Invalid input received while unparking vehicle", invalidInputException);
            throw new ParkingException(invalidInputException.getMessage(), invalidInputException);
        } catch (Exception e) {
            logger.error("error while unparking vehicle", e);
            throw new ParkingException(e.getMessage(), e);
        }

        return levelParkedVehicle;
    }

    public LevelParkedVehicle getValidLevelParkedVehicle(int levelParkedVehicleId) {
        //find out the parked vehicle
        LevelParkedVehicle levelParkedVehicle = levelParkedVehicleDao.getLevelParkedVehicleById(levelParkedVehicleId);
        return levelParkedVehicle;

    }

    public boolean removeLevelParkedVehicle(LevelParkedVehicle levelParkedVehicle) {
        levelParkedVehicleDao.delete(levelParkedVehicle);
        return true;
    }

    public Boolean emptySlot(LevelParkedVehicle levelParkedVehicle) {
        boolean isSlotEmptied = true;
        int levelNumber = levelParkedVehicle.getLevelNumber();
        int parkedVehicleId = levelParkedVehicle.getVehicleType();
        //find out level in which vehicle is parked
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
        return isSlotEmptied;
    }


}
