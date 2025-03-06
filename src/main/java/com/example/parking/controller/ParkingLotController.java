package com.example.parking.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.parking.models.ParkingLot;
import com.example.parking.services.ParkingLotService;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    /**
     * Retrieves all parking lots from the database.
     * 
     * @return List of all ParkingLot objects.
     */
    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotService.findAll();
    }

    /**
     * Deletes a ParkingLot by its ID.
     * If the ParkingLot does not exist, returns a 404 Not Found response.
     * 
     * @param id The ID of the ParkingLot to delete.
     * @return ResponseEntity with success or error message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParkingLot(@PathVariable Long id) {
        try {
            parkingLotService.deleteParkingLotById(id);
            return ResponseEntity.ok("Parking lot with id " + id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking lot not found");
        }
    }

    /**
     * Creates a new ParkingLot.
     * @param parkingLot The ParkingLot object to be created.
     * @return The saved ParkingLot object.
     */
    @PostMapping
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.createParkingLot(parkingLot);
    }

    /**
     * Updates an existing ParkingLot by its ID.
     * Handles HTTP PUT requests to "/api/parking-lots/{id}".
     * 
     * @param id The ID of the ParkingLot to update.
     * @param parkingLotDetails The new ParkingLot data received in the request body.
     * @return ResponseEntity containing the updated ParkingLot object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> updateParkingLot(@PathVariable Long id, @RequestBody ParkingLot parkingLotDetails) {
        return parkingLotService.updateParkingLot(id, parkingLotDetails);
    }
}