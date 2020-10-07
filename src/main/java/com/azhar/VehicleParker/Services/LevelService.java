package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.db.entities.Building.Level;
import org.springframework.stereotype.Component;

@Component
public interface LevelService {
    public LevelResponse insertLevel(Level level);

    public LevelResponse deleteLevel(Level level);

    public LevelResponse editLevel(Level level);

    public Boolean isLevelExist(Level level);

    public Boolean isLevelContainVehicles(Level level);
}
