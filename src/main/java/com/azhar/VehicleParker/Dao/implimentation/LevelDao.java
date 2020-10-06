package com.azhar.VehicleParker.Dao.implimentation;


import com.azhar.VehicleParker.InitialLoading;
import com.azhar.VehicleParker.db.entities.Building.Level;
import com.azhar.VehicleParker.db.dbClients.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDao implements com.azhar.VehicleParker.Dao.LevelDao {

    @Autowired
    InitialLoading initialLoading;
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
    public void delete(Level inputLevel) {
        levelRepository.delete(inputLevel);
    }

    @Override
    public Level getLevelByLevelNumber(int levelNumber) {
        return levelRepository.findLevelByLevelNumber(levelNumber);
    }

    @Override
    public List<Level> getLevelBySortedLevelNumber() {
        return levelRepository.findByOrderByLevelNumberAsc();
    }


}
