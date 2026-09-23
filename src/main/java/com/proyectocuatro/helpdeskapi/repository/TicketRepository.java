package com.proyectocuatro.helpdeskapi.repository;

import com.proyectocuatro.helpdeskapi.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository {

    private List<Ticket> tickets = new ArrayList<>();

    public Ticket guardar(Ticket ticket){
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> obtenerTodos() {
        return tickets;
    }

    public Optional<Ticket> obtenerPorID(String id){
        return tickets.stream()
                .filter(t -> t.getId().equalsIgnoreCase(id))
                .findFirst();
    }

}
