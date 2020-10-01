package com.azhar.VehicleParker.Services.Interfaces;

import com.azhar.VehicleParker.Entities.Level.LevelSpace;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SpaceManager {
    public List<LevelSpace> getLAvailableSpace();
}
