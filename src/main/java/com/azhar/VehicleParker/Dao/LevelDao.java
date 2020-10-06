package com.azhar.VehicleParker.Dao;


import com.azhar.VehicleParker.db.entities.Building.Level;

import java.util.List;


public interface LevelDao {
    public List<Level> getLevelList();

    public Level update(Level level);

    public Level insert(Level level);

    public void delete(Level inputLevel);

    public Level getLevelByLevelNumber(int levelNumber);

    public List<Level> getLevelBySortedLevelNumber();


}
