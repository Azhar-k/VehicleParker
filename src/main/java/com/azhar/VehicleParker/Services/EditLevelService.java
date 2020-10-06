package com.azhar.VehicleParker.Services;

import com.azhar.VehicleParker.Entities.ApiResponses.EditLevelResponse;
import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.Exceptions.VehicleNotFound;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.stereotype.Component;

@Component
public interface EditLevelService {
    public EditLevelResponse insertLevel(Level level);
    public EditLevelResponse deleteLevel(Level level);
    public EditLevelResponse editLevel(Level level);

    public  Boolean isLevelExist(Level level);
    public Boolean isLevelContainVehicles(Level level);
}
