package com.azhar.VehicleParker.Dao;


import com.azhar.VehicleParker.db.models.Building.Level;

import java.util.List;


public interface LevelDao {
    List<Level> getLevelList();

    Level update(Level level);

    Level insert(Level level);

    void delete(Level inputLevel);

    Level getLevelByLevelNumber(int levelNumber);

    List<Level> getLevelBySortedLevelNumber();


}
