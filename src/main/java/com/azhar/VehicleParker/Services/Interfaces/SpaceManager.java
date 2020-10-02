package com.azhar.VehicleParker.Services.Interfaces;


import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelVehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpaceManager {
    public List<LevelVehicle> getLevelVehicleList();
    public List<LevelSpace> getLAvailableSpace();

}
