package com.azhar.VehicleParker.Dao;

import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LevelDaoImp implements LevelDao{

    @Autowired
    Database database;

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    LevelParkedVehicleRepository levelParkedVehicleRepository;

    public List<Level> getLevelList(){
        return database.getLevelList();
    }

    public List<LevelSpace> getAvailableSpace() {

        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for (Level level : getLevelList()) {
            LevelSpace levelSpace = new LevelSpace(level.getLevelNumber());
            for (AllowedVehicle allowedVehicle:level.getAllowedVehicles()) {

                int freeSlot = allowedVehicle.getFreeSlots();
                levelSpace.getAvailabeSlots().put(allowedVehicle.getVehicle().getName(), freeSlot);

            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }


    public List<Vehicle> getVehicleList() {

        return vehicleRepository.findAll();
    }

    public List<LevelParkedVehicle> getLevelParkedVehicleList() {
        return levelParkedVehicleRepository.findAll();
    }

    public LevelParkedVehicle fillSlot(int availableLevelNumber, int type) {
        return database.fillSlot(availableLevelNumber,type);
    }

    public boolean emptySlot(LevelParkedVehicle levelVehicle) {
        return database.emptySlot(levelVehicle);
    }
}
