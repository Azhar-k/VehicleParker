package com.azhar.VehicleParker.Entities;

import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;

public class VehicleStatByType {
    private int count;
    private int totalAmount;
    private Vehicle vehicle;

    public VehicleStatByType(int count, Vehicle vehicle) {
        this.count = count;
        this.totalAmount = count*vehicle.getParkingRate();
        this.vehicle = vehicle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount() {
        this.totalAmount = this.getCount()*this.getVehicle().getParkingRate();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
