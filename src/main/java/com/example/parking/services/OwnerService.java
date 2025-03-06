package com.example.parking.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.parking.models.Owner;
import com.example.parking.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    /**
     * Retrieves all owners from the database.
     * 
     * @return List of all Owner objects.
     */
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    /*
     * Deletes an Owner by its ID.
     * If the Owner does not exist, throws an EntityNotFoundException.
     * 
     * @param id The ID of the Owner to delete.
     * @throws EntityNotFoundException if the Owner does not exist.
     */
    public void deleteOwnerById(Long id) {
        if (!ownerRepository.existsById(id)) {
            throw new EntityNotFoundException("Owner not found");
        }
        ownerRepository.deleteById(id);
    }

    /**
     * Saves a new Owner object in the database.
     * 
     * @param owner The Owner object to be saved.
     * @return The saved Owner object with its assigned ID.
     */
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public ResponseEntity<Owner> updateOwner(Long id, Owner ownerDetails) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Owner not found"));
        owner.setName(ownerDetails.getName());
        owner.setEmail(ownerDetails.getEmail());
        Owner updatedOwner = ownerRepository.save(owner);
        return ResponseEntity.ok(updatedOwner);
    }
}

