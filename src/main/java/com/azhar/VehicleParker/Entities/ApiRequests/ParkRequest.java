package com.azhar.VehicleParker.Entities.ApiRequests;

//Parking request should have vehicle name(type determined from name) and vehicle number
public class ParkRequest {
    private String vehicleName;
    private String vehicleNumber;

    public ParkRequest(String vehicleName, String vehicleNumber) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
    }

    public ParkRequest() {
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
