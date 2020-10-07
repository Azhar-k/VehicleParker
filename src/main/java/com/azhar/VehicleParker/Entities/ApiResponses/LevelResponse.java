package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.db.entities.Building.Level;

public class LevelResponse extends Response {

    private Level level;

    public LevelResponse(boolean isSucces, String message, Level level) {
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
