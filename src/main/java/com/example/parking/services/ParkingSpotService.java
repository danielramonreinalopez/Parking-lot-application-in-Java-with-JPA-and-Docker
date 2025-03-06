package com.example.parking.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.parking.models.ParkingSpot;
import com.example.parking.repository.ParkingSpotRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    /*
     * Retrieves all parking spots from the database.
     * 
     * @return List of all ParkingSpot objects.
     */
    public List<ParkingSpot> findAll() {
        return parkingSpotRepository.findAll();
    }

    /*
     * Deletes a ParkingSpot by its ID.
     * If the ParkingSpot does not exist, throws an EntityNotFoundException.
     * 
     * @param id The ID of the ParkingSpot to delete.
     * @throws EntityNotFoundException if the ParkingSpot does not exist.
     */
    public void deleteParkingSpotById(Long id) {
        if (!parkingSpotRepository.existsById(id)) {
            throw new EntityNotFoundException("Parking Spot not found");
        }
        parkingSpotRepository.deleteById(id);
    }

    /*
     * Saves a new ParkingSpot object in the database.
     * 
     * @param parkingSpot The ParkingSpot object to be saved.
     * @return The saved ParkingSpot object with its assigned ID.
     */
    public ParkingSpot createParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.save(parkingSpot);
    }

    public ResponseEntity<ParkingSpot> updateParkingSpot(Long id, ParkingSpot parkingSpotDetails) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parking spot not found"));
    
        parkingSpot.setSpotNumber(parkingSpotDetails.getSpotNumber());
        ParkingSpot updtedParkingSpot = parkingSpotRepository.save(parkingSpot);
        return ResponseEntity.ok(updtedParkingSpot);
    }
}
