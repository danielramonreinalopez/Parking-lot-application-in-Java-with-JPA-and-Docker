package com.example.parking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String spotNumber;

    @ManyToOne 
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;


    /*If I don't need the API to return the parkingSpots in the ParkingLot query,
     I add @JsonIgnore to prevent it from trying to serialize. 
     and FetchType.LAZY Avoid bringing information related to relationships.*/
    @OneToOne(mappedBy = "parkingSpot",fetch = FetchType.LAZY)
    @JsonIgnore
    private Vehicle vehicle;

    //builders
    public ParkingSpot(){}

    public ParkingSpot(Long id , String spotNumber){
      this.id= id;
      this.spotNumber = spotNumber;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}