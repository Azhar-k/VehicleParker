package com.azhar.VehicleParker.db.models.Building;

import com.azhar.VehicleParker.db.models.Vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class AllowedVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})//to handle serializer not found exception.
    private Vehicle vehicle;
    private int MAX_SLOTS;
    private int occupiedSlots;
    private int freeSlots;

    public AllowedVehicle() {
    }

    public AllowedVehicle(int MAX_SLOTS, int occupiedSlots, Vehicle vehicle) {

        this.MAX_SLOTS = MAX_SLOTS;
        this.occupiedSlots = occupiedSlots;
        this.freeSlots = this.MAX_SLOTS - this.occupiedSlots;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMAX_SLOTS() {
        return MAX_SLOTS;
    }

    public void setMAX_SLOTS(int MAX_SLOTS) {
        this.MAX_SLOTS = MAX_SLOTS;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public void setOccupiedSlots(int occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public int getFreeSlots() {
        return this.getMAX_SLOTS() - this.getOccupiedSlots();
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "AllowedVehicle{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", MAX_SLOTS=" + MAX_SLOTS +
                ", occupiedSlots=" + occupiedSlots +
                ", freeSlots=" + freeSlots +
                '}';
    }
}
