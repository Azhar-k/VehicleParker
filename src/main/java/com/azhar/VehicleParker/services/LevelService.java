package com.azhar.VehicleParker.services;

import com.azhar.VehicleParker.Entities.Exceptions.LevelException;
import com.azhar.VehicleParker.db.models.Building.Level;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LevelService {
    List<Level> getSortedLevels();

    Level insertLevel(Level level) throws LevelException;

    boolean deleteLevel(Level level) throws LevelException;

    Level editLevel(Level level) throws LevelException;

}
