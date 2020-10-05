package com.azhar.VehicleParker.Entities.ApiRequests;

public class ParkRequest {
    private String VehicleName;
    private String vehicleNumber;

    public ParkRequest(String vehicleName, String vehicleNumber) {
        VehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
    }

    public ParkRequest() {
    }

    public String getVehicleName() {
        return VehicleName;
    }

    public void setVehicleName(String vehicleName) {
        VehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
