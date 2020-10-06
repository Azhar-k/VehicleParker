package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.db.entities.Vehicle.Vehicle;

public class EditVehicleResponse extends Response {

    private Vehicle vehicle;

    public EditVehicleResponse(boolean isSucces, String message, Vehicle vehicle) {
        super(isSucces, message);
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
