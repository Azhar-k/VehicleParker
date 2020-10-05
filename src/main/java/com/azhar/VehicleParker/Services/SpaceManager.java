package com.azhar.VehicleParker.Services;


import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpaceManager {
    public List<LevelParkedVehicle> getLevelVehicleList();
    public List<LevelSpace> getLAvailableSpace();

}
