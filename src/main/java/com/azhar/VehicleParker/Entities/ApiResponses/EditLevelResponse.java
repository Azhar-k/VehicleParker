package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.Entities.Building.Level;
import com.azhar.VehicleParker.Entities.LevelParkedVehicle;

public class EditLevelResponse extends Response {

    private Level level;

    public EditLevelResponse(boolean isSucces, String message, Level level) {
        super(isSucces, message);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
