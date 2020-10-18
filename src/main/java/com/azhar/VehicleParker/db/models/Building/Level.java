package com.azhar.VehicleParker.db.models.Building;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Level {
    @Id
    @NotNull
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
