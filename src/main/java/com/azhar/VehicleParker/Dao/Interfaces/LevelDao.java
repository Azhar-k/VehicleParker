package com.azhar.VehicleParker.Dao.Interfaces;


import com.azhar.VehicleParker.Entities.Building.AllowedVehicle;
import com.azhar.VehicleParker.Entities.Building.Level;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LevelDao {
    public List<Level> getLevelList();
    public Level update(Level level);
    public Level insert(Level level);

    public Level getLevelByLevelNumber(int levelNumber);


}
