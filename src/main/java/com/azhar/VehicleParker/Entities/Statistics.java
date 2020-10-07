package com.azhar.VehicleParker.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private LocalDate localDate;
    private List<VehicleStatByType> vehicleStatByTypeList = new ArrayList<VehicleStatByType>();

    public Statistics(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void addVehicleStatByType(VehicleStatByType vehicleStatByType){
        this.getVehicleStatByTypeList().add(vehicleStatByType);
    }
    public void removeVehicleStatByType(VehicleStatByType vehicleStatByType){
        this.getVehicleStatByTypeList().remove(vehicleStatByType);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<VehicleStatByType> getVehicleStatByTypeList() {
        return vehicleStatByTypeList;
    }

    public void setVehicleStatByTypeList(List<VehicleStatByType> vehicleStatByTypeList) {
        this.vehicleStatByTypeList = vehicleStatByTypeList;
    }
}
