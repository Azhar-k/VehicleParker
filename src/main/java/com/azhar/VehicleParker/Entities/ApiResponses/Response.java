package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.Entities.LevelParkedVehicle;

public class Response {
    private boolean isSucces;
    private String message;


    public Response(boolean isSucces, String message) {
        this.isSucces = isSucces;
        this.message = message;
    }

    public boolean isSucces() {
        return isSucces;
    }

    public void setSucces(boolean succes) {
        isSucces = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
