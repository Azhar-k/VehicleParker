package com.azhar.VehicleParker.Dao.implimentation;


import com.azhar.VehicleParker.Database;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.dbclient.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDao implements com.azhar.VehicleParker.Dao.LevelDao {

    @Autowired
    Database database;
    @Autowired
    LevelRepository levelRepository;


    public List<Level> getLevelList() {
        return levelRepository.findAll();
    }

    @Override
    public Level update(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level insert(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level getLevelByLevelNumber(int levelNumber) {
        return levelRepository.findLevelByLevelNumber(levelNumber);
    }




}
