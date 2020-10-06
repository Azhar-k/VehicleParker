package com.azhar.VehicleParker.Services;


import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Building.LevelSpace;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;
import com.azhar.VehicleParker.Entities.Vehicle.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpaceManager {
    public List<LevelParkedVehicle> getLevelVehicleList();
    public List<LevelSpace> getLAvailableSpace();
    public List<Level> getLevelList();
    public List<Vehicle> getVehicles();

}
