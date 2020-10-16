package com.azhar.VehicleParker.db.models.Building;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Level {
    @Id
    private int number;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "level")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<AllowedVehicle> allowedVehicles;


    public Level() {
    }


    public Level(int number, List<AllowedVehicle> allowedVehicles) {
        this.number = number;
        this.allowedVehicles = allowedVehicles;
    }

    public Level(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAllowedVehicles(List<AllowedVehicle> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public List<AllowedVehicle> getAllowedVehicles() {
        return allowedVehicles;
    }


    @Override
    public String toString() {
        return "Level{" +
                "number=" + number +
                ", allowedVehicles=" + allowedVehicles +
                '}';
    }
}
