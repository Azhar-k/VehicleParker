package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.db.models.Building.Level;
import org.springframework.stereotype.Component;

@Component
public interface LevelService {
    LevelResponse insertLevel(Level level);

    LevelResponse deleteLevel(Level level);

    LevelResponse editLevel(Level level);

    Boolean isLevelExist(Level level);

    Boolean isLevelContainVehicles(Level level);
}
