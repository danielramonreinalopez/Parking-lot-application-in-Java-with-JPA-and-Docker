package com.example.parking.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.parking.models.Ticket;
import com.example.parking.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Retrieves all tickets from the database.
     * 
     * @return List of all Ticket objects.
     */
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    /**
     * Deletes a Ticket by its ID.
     * If the Ticket does not exist, throws an EntityNotFoundException.
     * 
     * @param id The ID of the Ticket to delete.
     * @throws EntityNotFoundException if the Ticket does not exist.
     */
    public void deleteTicketById(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }

    /**
     * Saves a new Ticket object in the database.
     * 
     * @param ticket The Ticket object to be saved.
     * @return The saved Ticket object with its assigned ID.
     */
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public ResponseEntity<Ticket> updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ticket not found"));
    
        ticket.setEntryTime(ticketDetails.getEntryTime());
        ticket.setExitTime(ticketDetails.getExitTime());
        Ticket updatedTicket = ticketRepository.save(ticket);
        return ResponseEntity.ok(updatedTicket);
    }
}
