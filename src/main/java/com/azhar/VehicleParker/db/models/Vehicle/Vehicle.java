package com.azhar.VehicleParker.db.models.Vehicle;


import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    @NotNull
    private String name;
    @NotNull
    private int parkingRate;

    public Vehicle(String name) {
        this.name = name;
    }

    public Vehicle(int id, String name, int parkingRate) {
        this.id = id;
        this.name = name;
        this.parkingRate = parkingRate;
    }

    public Vehicle(String name, int parkingRate) {
        this.name = name;
        this.parkingRate = parkingRate;
    }

    public Vehicle() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(int parkingRate) {
        this.parkingRate = parkingRate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
