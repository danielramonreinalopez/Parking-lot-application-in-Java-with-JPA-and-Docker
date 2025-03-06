package com.example.parking.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.parking.models.Vehicle;
import com.example.parking.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    /*
     * Retrieves all vehicles from the database.
     * 
     * @return List of all Vehicle objects.
     */
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    /*
     * Deletes a Vehicle by its ID.
     * If the Vehicle does not exist, throws an EntityNotFoundException.
     * 
     * @param id The ID of the Vehicle to delete.
     * @throws EntityNotFoundException if the Vehicle does not exist.
     */
    public void deleteVehicleById(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicle not found");
        }
        vehicleRepository.deleteById(id);
    }

    /*
     * Saves a new Vehicle object in the database.
     * 
     * @param vehicle The Vehicle object to be saved.
     * @return The saved Vehicle object with its assigned ID.
     */
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public ResponseEntity <Vehicle> updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
    
        vehicle.setLicensePlate(vehicleDetails.getLicensePlate());
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }
}
