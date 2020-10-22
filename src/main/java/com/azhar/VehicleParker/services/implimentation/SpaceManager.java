package com.azhar.VehicleParker.services.implimentation;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Dao.LevelParkedVehicleDao;
import com.azhar.VehicleParker.db.models.Building.AllowedVehicle;
import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpaceManager implements com.azhar.VehicleParker.services.SpaceManager {

    @Autowired
    LevelDao levelDao;
    @Autowired
    LevelParkedVehicleDao levelParkedVehicleDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public List<LevelParkedVehicle> getLevelParkedVehicleList() {
        return levelParkedVehicleDao.getAll();
    }

    @Override
    public List<LevelSpace> getAvailableSpaceList() throws Exception {
        //create a list of available space by checking all the levels
        List<LevelSpace> availableSpace = null;
        try {
            availableSpace = buildAvailableSpaceList();
        } catch (Exception e) {
            logger.error("Error while creating available space", e);
            throw new Exception("Error while creating available space");
        }
        return availableSpace;
    }

    private List<LevelSpace> buildAvailableSpaceList() {
        List<LevelSpace> availableSpace = new ArrayList<LevelSpace>();
        List<Level> levelList = levelDao.getAllSortedByLevelNumber();
        for (Level level : levelList) {
            LevelSpace levelSpace = new LevelSpace(level.getNumber());
            for (AllowedVehicle allowedVehicle : level.getAllowedVehicles()) {
                int freeSlot = allowedVehicle.getFreeSlots();
                Map<String, Integer> availableSlotsMap = levelSpace.getAvailabeSlots();
                availableSlotsMap.put(allowedVehicle.getVehicle().getName(), freeSlot);
            }
            availableSpace.add(levelSpace);

        }
        return availableSpace;
    }
}
