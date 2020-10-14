package com.azhar.VehicleParker.Services.implimentation;

import com.azhar.VehicleParker.Dao.AllowedVehicleDao;
import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.Dao.VehicleDao;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SpaceManager implements com.azhar.VehicleParker.Services.SpaceManager {

    @Autowired
    LevelDao levelDao;

    @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    AllowedVehicleDao allowedVehicleDao;


    @Override
    public List<LevelParkedVehicle> getLevelVehicleList() {
        return levelParkedVehicleDao.getLevelParkedVehicleList();
    }

    @Override
    public List<LevelSpace> getLAvailableSpace() {
        //create a list of available space by checking all the levels
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        List<Level> levelList = levelDao.getLevelList();
        levelList.sort(new SortbyLevelNumber());
        for (Level level : levelList) {
            LevelSpace levelSpace = new LevelSpace(level.getNumber());
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                int freeSlot = allowedVehicle.getFreeSlots();
                levelSpace.getAvailabeSlots().put(allowedVehicle.getVehicle().getName(), freeSlot);

            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }


    @Override
    public List<Level> getLevelList() {
        return levelDao.getLevelBySortedLevelNumber();
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleDao.getVehicleList();
    }


    class SortbyLevelNumber implements Comparator<Level> {
        public int compare(Level a, Level b) {
            return a.getNumber() - b.getNumber();
        }
    }

}
