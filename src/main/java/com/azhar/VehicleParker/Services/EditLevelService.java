package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.Building.Level;

public interface EditLevelService {
    public Level insertLevel(Level level);
    public boolean deleteLevel(Level level);
    public Level editLevel(Level level);
    public  Boolean isLevelExist(Level level);
    public Boolean isLevelContainVehicles(Level level);
}
