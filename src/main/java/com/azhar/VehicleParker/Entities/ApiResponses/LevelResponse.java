package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.db.models.Building.Level;

//Used to provide responses for all services of LevelService
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
