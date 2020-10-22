package com.azhar.VehicleParker.services;


import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpaceManager {
    List<LevelParkedVehicle> getLevelParkedVehicleList();

    List<LevelSpace> getAvailableSpaceList() throws Exception;

}
