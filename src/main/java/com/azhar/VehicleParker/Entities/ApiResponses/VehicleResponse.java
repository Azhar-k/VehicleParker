package com.azhar.VehicleParker.Entities.ApiResponses;

import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
//Used to provide responses for all services of VehicleResponse
public class VehicleResponse extends Response {

    private Vehicle vehicle;

    public VehicleResponse(boolean isSucces, String message, Vehicle vehicle) {
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
