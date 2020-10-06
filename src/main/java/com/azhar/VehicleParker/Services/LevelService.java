package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.EditLevelResponse;
import com.azhar.VehicleParker.db.entities.Building.Level;
import org.springframework.stereotype.Component;

@Component
public interface LevelService {
    public EditLevelResponse insertLevel(Level level);

    public EditLevelResponse deleteLevel(Level level);

    public EditLevelResponse editLevel(Level level);

    public Boolean isLevelExist(Level level);

    public Boolean isLevelContainVehicles(Level level);
}
