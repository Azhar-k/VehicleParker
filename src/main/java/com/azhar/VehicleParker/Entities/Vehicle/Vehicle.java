package com.azhar.VehicleParker.Entities.Vehicle;

import javax.persistence.*;

@Table(name = "Vehicle")
@Entity
public class Vehicle {
    @Id
    private int id;
    private String name;

    public Vehicle(String name) {
        this.name = name;
    }

    public Vehicle(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "type=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
