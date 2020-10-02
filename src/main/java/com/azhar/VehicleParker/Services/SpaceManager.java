package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceManager {

    @Autowired
    LevelDao levelDao;
    public List<Level> getLLevelInfo(){

        return levelDao.getLevelList();
    }
    public List<LevelSpace> getLAvailableSpace(){

        return levelDao.getAvailableSpace();
    }


    public List<LevelVehicle> getLevelVehicleList() {
        return levelDao.getLevelVehicleList();
    }
}
