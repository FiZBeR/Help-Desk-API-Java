package com.proyectocuatro.helpdeskapi.controller;


import com.proyectocuatro.helpdeskapi.dto.TicketDTO;
import com.proyectocuatro.helpdeskapi.model.Ticket;
import com.proyectocuatro.helpdeskapi.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> obtenerTodos(){
        List<Ticket> tickets = ticketService.obtenerTodos();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@RequestBody TicketDTO dto){
        Ticket nuevoTicket = ticketService.crearTicket(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTicket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerPorID(@PathVariable String id){
        Ticket ticketSelect = ticketService.obtenerPorID(id);
        return ResponseEntity.ok(ticketSelect);
    }

    @PutMapping("/{id}/resolver/")
    public ResponseEntity<Ticket> resolverTicket(@PathVariable String id){
        Ticket ticketResuleto = ticketService.resolverTicket(id);
        return ResponseEntity.ok(ticketResuleto);
    }
}
