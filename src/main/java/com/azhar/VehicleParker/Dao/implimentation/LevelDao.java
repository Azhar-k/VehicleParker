package com.azhar.VehicleParker.Dao.implimentation;


import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.db.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDao implements com.azhar.VehicleParker.Dao.LevelDao {

    @Autowired
    LevelRepository levelRepository;


    public List<Level> getAll() {
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
    public Level getByNumber(int levelNumber) {

        return levelRepository.findLevelByNumber(levelNumber);
    }

    @Override
    public List<Level> getAllSortedByLevelNumber() {
        return levelRepository.findByOrderByNumberAsc();
    }


}
