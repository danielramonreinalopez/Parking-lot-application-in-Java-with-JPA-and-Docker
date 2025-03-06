package com.example.parking.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.parking.models.Ticket;
import com.example.parking.services.TicketService;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        try {
            ticketService.deleteTicketById(id);
            return ResponseEntity.ok("Ticket with id " + id + " successfully removed");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found");
        }
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket>updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        //TODO: process PUT request
        return ticketService.updateTicket(id, ticketDetails);
    }
}
