package com.example.parking.models;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String email;
    
    /*If I don't need the API to return the parkingSpots in the ParkingLot query,
     I add @JsonIgnore to prevent it from trying to serialize. 
     and FetchType.EAGER is used in entity relationships to indicate that the 
     related data should be loaded immediately when the parent entity is queried.*/
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,orphanRemoval = true,  fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Vehicle> vehicles;

    // builders
    public Owner(){}

    public Owner (Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
