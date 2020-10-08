package com.azhar.VehicleParker.UnitTests.Services;


import com.azhar.VehicleParker.db.models.Building.Level;
import com.azhar.VehicleParker.Entities.LevelSpace;
import com.azhar.VehicleParker.db.models.LevelParkedVehicle;
import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpaceManager {
    List<LevelParkedVehicle> getLevelVehicleList();

    List<LevelSpace> getLAvailableSpace();

    List<Level> getLevelList();

    List<Vehicle> getVehicles();

}
