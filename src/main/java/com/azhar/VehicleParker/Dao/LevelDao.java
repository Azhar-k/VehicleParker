package com.azhar.VehicleParker.Dao;


import com.azhar.VehicleParker.db.models.Building.Level;

import java.util.List;


public interface LevelDao {
    List<Level> getAll();

    Level update(Level level);

    Level insert(Level level);

    void delete(Level inputLevel);

    Level getByNumber(int levelNumber);

    List<Level> getAllSortedByLevelNumber();


}
