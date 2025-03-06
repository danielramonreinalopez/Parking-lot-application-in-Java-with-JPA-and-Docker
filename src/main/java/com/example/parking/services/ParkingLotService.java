package com.example.parking.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.parking.models.ParkingLot;
import com.example.parking.repository.ParkingLotRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    /*
     * Retrieves all parking lots from the database.
     * 
     * @return List of all ParkingLot objects.
     */
    public List<ParkingLot> findAll() {
        List<ParkingLot> parkingLotToReturn = new ArrayList<>();
        parkingLotToReturn = parkingLotRepository.findAll();
        return parkingLotToReturn;
    }

    /*
     * Deletes a ParkingLot by its ID.
     * If the ParkingLot does not exist, throws an EntityNotFoundException.
     * 
     * @param id The ID of the ParkingLot to delete.
     * @throws EntityNotFoundException if the ParkingLot does not exist.
     */
    public void deleteParkingLotById(Long id) {
        if (!parkingLotRepository.existsById(id)) {
            throw new EntityNotFoundException("Parking Lot not found");
        }
        parkingLotRepository.deleteById(id);
    }

    /*
     * Saves a new ParkingLot object in the database.
     * 
     * @param parkingLot The ParkingLot object to be saved.
     * @return The saved ParkingLot object with its assigned ID.
     */
    public ParkingLot createParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    /*
     * Updates an existing ParkingLot by its ID.
     * If the ParkingLot does not exist, an exception is thrown.
     * 
     * @param id The ID of the ParkingLot to update.
     * @param parkingLotDetails The new data for the ParkingLot.
     * @return The updated ParkingLot object.
     * @throws EntityNotFoundException if the ParkingLot does not exist.
     */
    public ResponseEntity<ParkingLot> updateParkingLot(Long id, ParkingLot parkingLotDetails) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parking lot not found"));

        parkingLot.setName(parkingLotDetails.getName());
        parkingLot.setLocation(parkingLotDetails.getLocation());

        ParkingLot updatedParkingLot = parkingLotRepository.save(parkingLot);
        return ResponseEntity.ok(updatedParkingLot);
    }
    
}
