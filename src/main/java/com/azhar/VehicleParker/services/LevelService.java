package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.ApiResponses.LevelResponse;
import com.azhar.VehicleParker.db.models.Building.Level;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LevelService {
    List<Level> getSortedLevels();

    LevelResponse insertLevel(Level level);

    LevelResponse deleteLevel(Level level);

    LevelResponse editLevel(Level level);

}
