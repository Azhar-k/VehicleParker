package com.azhar.VehicleParker.Services.Implimentations;

import com.azhar.VehicleParker.Dao.LevelDao;
import com.azhar.VehicleParker.Entities.Level.LevelSpace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceManager implements com.azhar.VehicleParker.Services.Interfaces.SpaceManager {

    @Autowired
    LevelDao levelDao;
    public List<LevelSpace> getLAvailableSpace(){
        //return a list containing level number and free space in that level
        return levelDao.getAvailableSpace();
    }


}
