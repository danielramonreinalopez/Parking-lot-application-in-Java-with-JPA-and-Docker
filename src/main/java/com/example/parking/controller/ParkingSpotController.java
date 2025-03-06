package com.example.parking.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.parking.models.ParkingSpot;
import com.example.parking.services.ParkingSpotService;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/parking-spots")
public class ParkingSpotController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping
    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParkingSpot(@PathVariable Long id) {
        try {
            parkingSpotService.deleteParkingSpotById(id);
            return ResponseEntity.ok("Parking spot with id " + id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found");
        }
    }

    @PostMapping
    public ParkingSpot createParkingSpot(@RequestBody ParkingSpot parkingSpot) {
        return parkingSpotService.createParkingSpot(parkingSpot);
    }

    @PutMapping("/{id}")
public ResponseEntity<ParkingSpot> updateParkingSpot(@PathVariable Long id, @RequestBody ParkingSpot parkingSpotDetails) {
    return parkingSpotService.updateParkingSpot(id, parkingSpotDetails);
}

}
