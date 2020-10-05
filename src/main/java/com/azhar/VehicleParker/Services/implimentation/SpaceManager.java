package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpaceManager implements com.azhar.VehicleParker.Services.SpaceManager {

    @Autowired
    LevelDao levelDao;

    @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;


    public List<LevelParkedVehicle> getLevelVehicleList() {
        return levelParkedVehicleDao.getLevelParkedVehicleList();
    }

    @Override
    public List<LevelSpace> getLAvailableSpace() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        for (Level level : levelDao.getLevelList()) {
            LevelSpace levelSpace = new LevelSpace(level.getLevelNumber());
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                int freeSlot = allowedVehicle.getFreeSlots();
                levelSpace.getAvailabeSlots().put(allowedVehicle.getVehicle().getName(), freeSlot);

            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }
}
