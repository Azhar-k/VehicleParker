package com.azhar.VehicleParker.Entities.Requests;


import org.springframework.stereotype.Component;


public class ParkRequest {
    private String type;

    public ParkRequest() {
    }

    public ParkRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
